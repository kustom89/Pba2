package com.example.andre.pba2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andre.pba2.MainActivity;
import com.example.andre.pba2.PendingFoodClickListener;
import com.example.andre.pba2.PendingsFoodFragment;
import com.example.andre.pba2.R;
import com.example.andre.pba2.data.Queries;
import com.example.andre.pba2.models.PendingFood;

import java.util.List;

public class PendingsFoodAdapter extends RecyclerView.Adapter<PendingsFoodAdapter.VieHolder> {

    private PendingFoodClickListener listener;

    private List<PendingFood> pendingFoods=new Queries().pendingFoods();


    public PendingsFoodAdapter(MainActivity mainActivity) {
        this.listener = listener;
    }

    public PendingsFoodAdapter(PendingsFoodFragment pendingsFoodFragment) {

    }


    @Override
    public VieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending_food,parent,false);
        VieHolder viewHolder= new VieHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VieHolder holder, int position) {
        final PendingFood pendingFood=pendingFoods.get(position);
        holder.textView.setText(pendingFood.getName());
        holder.textView2.setText(pendingFood.getDescripcion());
        holder.checkBox.setChecked(pendingFood.isDone());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingFood auxFood= pendingFoods.get(holder.getAdapterPosition());
                listener.clickedID(auxFood.getId());
            }
        });


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int auxPosition=holder.getAdapterPosition();
                            PendingFood auxPendingFood= pendingFoods.get(auxPosition);
                            auxPendingFood.save();
                            pendingFoods.remove(auxPosition);
                            notifyItemRemoved(auxPosition);


                        }
                    },400);
                }
            }
        });





    }

    @Override
    public int getItemCount() {
        return pendingFoods.size();
    }

    public void update(PendingFood pendingFood){
        pendingFoods.add(pendingFood);
        notifyDataSetChanged();
    }

    public void update(PendingsFoodAdapter pendingsFoodAdapter) {
    }


    static class VieHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView textView;
        private TextView textView2;
        private LinearLayout itemLayout;

        public VieHolder(View itemView) {
            super(itemView);

            checkBox= itemView.findViewById(R.id.pendingFoodCb);
            textView= itemView.findViewById(R.id.pendingTv);
            textView2=itemView.findViewById(R.id.pendingTv2);
            itemLayout=itemView.findViewById(R.id.foodL1);



        }


    }
}
