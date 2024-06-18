package ru.mirea.carbuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.carbuy.R;
import ru.mirea.carbuy.model.Ads;

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.MyViewHolder> {
    private Context context;
    private List<Ads> adsList;
    HandleCounterClick clickListener;

    public MyAdsAdapter(Context context, List<Ads> adsList, HandleCounterClick clickListener) {
        this.context = context;
        this.adsList = adsList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ads ads = adsList.get(position);

        holder.markCar.setText(ads.getMarkCar());
        holder.priceCar.setText(ads.getPriceCar());
        holder.numTel.setText(ads.getNumTelephone());

        holder.deleteItem.setOnClickListener(v -> {
            clickListener.deleteItem(ads);
        });

        holder.editItem.setOnClickListener(v -> {
            clickListener.editItem(ads);
        });
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView markCar;
        TextView priceCar;
        TextView numTel;
        Button editItem;
        Button deleteItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            markCar = itemView.findViewById(R.id.markCarItem);
            priceCar = itemView.findViewById(R.id.priceCarItem);
            numTel = itemView.findViewById(R.id.numTelItem);
            editItem = itemView.findViewById(R.id.editItem);
            deleteItem = itemView.findViewById(R.id.deleteItem);
        }
    }

    public void setAdsList(List<Ads> adsList) {
        this.adsList = adsList;
        notifyDataSetChanged();
    }


    public interface HandleCounterClick {
        void deleteItem(Ads adsItem);

        void editItem(Ads adsItem);
        void createItem(Ads adsItem);
    }
}
