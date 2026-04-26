package com.example.supervisionlivraisonmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supervisionlivraisonmobile.model.Delivery;
import com.example.supervisionlivraisonmobile.network.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourneeFragment extends Fragment {

    private RecyclerView rvTournee;
    private TextView tvZoneInfo;
    private DeliveryAdapter adapter;
    private int livreurId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tournee, container, false);

        rvTournee = view.findViewById(R.id.rvTournee);
        tvZoneInfo = view.findViewById(R.id.tvZoneInfo);
        
        // Récupérer l'ID du livreur depuis l'activité
        Intent intent = getActivity().getIntent();
        livreurId = intent.getIntExtra("idpers", -1);

        setupRecyclerView();
        loadDeliveries();

        return view;
    }

    private void setupRecyclerView() {
        adapter = new DeliveryAdapter(new ArrayList<>());
        adapter.setOnDeliveryClickListener(new DeliveryAdapter.OnDeliveryClickListener() {
            @Override
            public void onDeliveryClick(Delivery delivery) {
                showDeliveryDetail(delivery);
            }

            @Override
            public void onContactClick(Delivery delivery) {
                if (delivery.getTelClient() != null) {
                    android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_DIAL);
                    intent.setData(android.net.Uri.parse("tel:" + delivery.getTelClient()));
                    startActivity(intent);
                }
            }
        });
        rvTournee.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTournee.setAdapter(adapter);
    }

    private void loadDeliveries() {
        if (livreurId == -1) return;

        RetrofitClient.getApiService().getMyDeliveries(livreurId).enqueue(new Callback<List<Delivery>>() {
            @Override
            public void onResponse(Call<List<Delivery>> call, Response<List<Delivery>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Delivery> deliveries = response.body();
                    adapter.setDeliveries(deliveries);
                    adapter.notifyDataSetChanged();
                    
                    if (!deliveries.isEmpty()) {
                        // On prend la ville de la première livraison comme zone (définie par le contrôleur)
                        String zone = deliveries.get(0).getAdresseClient();
                        if (zone != null && zone.contains(",")) {
                            zone = zone.substring(zone.lastIndexOf(",") + 1).trim();
                        }
                        tvZoneInfo.setText("Zone: " + zone);
                    } else {
                        tvZoneInfo.setText("Zone: Ariana"); // Fallback
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Delivery>> call, Throwable t) {
                Toast.makeText(getContext(), "Erreur de chargement: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDeliveryDetail(Delivery delivery) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View sheetView = getLayoutInflater().inflate(R.layout.layout_delivery_detail_sheet, null);
        bottomSheetDialog.setContentView(sheetView);

        TextView tvName = sheetView.findViewById(R.id.sheetClientName);
        TextView tvAddress = sheetView.findViewById(R.id.sheetAddress);
        Button btnStart = sheetView.findViewById(R.id.btnStartDelivery);
        Button btnDelivered = sheetView.findViewById(R.id.btnMarkDelivered);
        Button btnUnreachable = sheetView.findViewById(R.id.btnUnreachable);
        Button btnNotPaid = sheetView.findViewById(R.id.btnNotPaid);
        Button btnCanceled = sheetView.findViewById(R.id.btnCanceled);

        tvName.setText(delivery.getFullClientName());
        String info = delivery.getAdresseClient() + "\nTotal: " + String.format(Locale.getDefault(), "%.3f DT", delivery.getMontant());
        tvAddress.setText(info);

        btnStart.setOnClickListener(v -> {
            updateStatus(delivery.getNocde(), "En cours", "Livraison démarrée");
            bottomSheetDialog.dismiss();
        });

        btnDelivered.setOnClickListener(v -> {
            updateStatus(delivery.getNocde(), "Livré", "Succès");
            bottomSheetDialog.dismiss();
        });

        btnUnreachable.setOnClickListener(v -> {
            updateStatus(delivery.getNocde(), "Injoignable", "Le client ne répond pas");
            bottomSheetDialog.dismiss();
        });

        btnNotPaid.setOnClickListener(v -> {
            updateStatus(delivery.getNocde(), "Non payé", "Le client a refusé de payer");
            bottomSheetDialog.dismiss();
        });

        btnCanceled.setOnClickListener(v -> {
            updateStatus(delivery.getNocde(), "Annulé", "Annulation client");
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

    private void updateStatus(int nocde, String status, String remarque) {
        Map<String, String> update = new HashMap<>();
        update.put("etatliv", status);
        update.put("remarque", remarque);

        RetrofitClient.getApiService().updateStatus(nocde, update).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Statut mis à jour : " + status, Toast.LENGTH_SHORT).show();
                    loadDeliveries(); // Recharger la liste
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(getContext(), "Erreur mise à jour: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
