package com.example.supervisionlivraisonmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.supervisionlivraisonmobile.model.Delivery;
import java.util.List;
import java.util.Locale;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private List<Delivery> deliveries;
    private OnDeliveryClickListener listener;

    public interface OnDeliveryClickListener {
        void onDeliveryClick(Delivery delivery);
        void onContactClick(Delivery delivery);
    }

    public DeliveryAdapter(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setOnDeliveryClickListener(OnDeliveryClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        Delivery delivery = deliveries.get(position);
        holder.tvOrderId.setText("#CDE-" + delivery.getNocde());
        holder.tvClientName.setText(delivery.getFullClientName());
        holder.tvAddress.setText(delivery.getAdresseClient());
        holder.tvStatus.setText(delivery.getEtatliv().toUpperCase());
        
        // Status Colors
        int bgColor, textColor;
        switch (delivery.getEtatliv()) {
            case "En cours":
                bgColor = 0xFFFFF3E0; textColor = 0xFFE65100; // Orange
                break;
            case "Livré":
                bgColor = 0xFFE8F5E9; textColor = 0xFF2E7D32; // Green
                break;
            case "Non payé":
                bgColor = 0xFFFFEBEE; textColor = 0xFFC62828; // Red
                break;
            case "Injoignable":
                bgColor = 0xFFF3E5F5; textColor = 0xFF7B1FA2; // Purple
                break;
            case "Annulé":
                bgColor = 0xFFECEFF1; textColor = 0xFF455A64; // Gray
                break;
            default:
                bgColor = 0xFFF5F5F5; textColor = 0xFF212121; // Default Gray
                break;
        }
        
        android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
        shape.setCornerRadius(12 * holder.itemView.getContext().getResources().getDisplayMetrics().density);
        shape.setColor(bgColor);
        holder.tvStatus.setBackground(shape);
        holder.tvStatus.setTextColor(textColor);
        
        if (delivery.getMontant() != null) {
            holder.tvAmount.setText(String.format(Locale.getDefault(), "%.3f DT", delivery.getMontant()));
        } else {
            holder.tvAmount.setText("0.000 DT");
        }

        if (holder.btnContact != null) {
            holder.btnContact.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onContactClick(delivery);
                }
            });
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeliveryClick(delivery);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvClientName, tvAddress, tvStatus, tvAmount;
        View btnContact;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            btnContact = itemView.findViewById(R.id.btnContact);
        }
    }
}
