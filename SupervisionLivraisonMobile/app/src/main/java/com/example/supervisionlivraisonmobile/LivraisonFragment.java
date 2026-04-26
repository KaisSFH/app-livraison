package com.example.supervisionlivraisonmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supervisionlivraisonmobile.model.Delivery;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class LivraisonFragment extends Fragment {

    private RecyclerView rvDeliveries;
    private DeliveryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livraison, container, false);

        rvDeliveries = view.findViewById(R.id.rvDeliveries);

        setupRecyclerView();
        
        return view;
    }

    private void setupRecyclerView() {
        List<Delivery> mockData = new ArrayList<>();
        mockData.add(new Delivery(1023, "Jean Dupont", "123 Rue de la Liberté, Ariana", "En cours", "Ahmed"));
        mockData.add(new Delivery(1024, "Marie Martin", "45 Av des FAR, Ariana", "Terminé", "Said"));
        mockData.add(new Delivery(1025, "Karim Benani", "Boulvard Zerktouni, Ariana", "En prévu", "Youssef"));

        adapter = new DeliveryAdapter(mockData);
        adapter.setOnDeliveryClickListener(new DeliveryAdapter.OnDeliveryClickListener() {
            @Override
            public void onDeliveryClick(Delivery delivery) {
                // Show details sheet (existing behavior)
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
        rvDeliveries.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDeliveries.setAdapter(adapter);
    }
}
