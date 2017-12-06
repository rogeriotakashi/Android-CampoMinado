package com.example.rogerio.campominado.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.model.PersonalRecord;

import java.util.List;

/**
 * Created by ROGERIO on 06/12/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.PersonalRecordViewHolder> {

    List<PersonalRecord> records;



    public RecycleViewAdapter(List<PersonalRecord> records){
        this.records = records;
    }

    @Override
    public PersonalRecordViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        PersonalRecordViewHolder pvh = new PersonalRecordViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonalRecordViewHolder PersonalRecordViewHolder, int position) {
        //TextViews
        PersonalRecordViewHolder.nickname.setText(records.get(position).getNickname());
        PersonalRecordViewHolder.time.setText(records.get(position).getTime());
        PersonalRecordViewHolder.difficulty.setText(records.get(position).getDifficulty());

        //ImageViews
        PersonalRecordViewHolder.img1.setImageResource(R.drawable.win);
        PersonalRecordViewHolder.img2.setImageResource(R.drawable.chronometer);
        PersonalRecordViewHolder.img3.setImageResource(R.drawable.star);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }








    public static class PersonalRecordViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nickname;
        TextView time;
        TextView difficulty;
        ImageView img1;
        ImageView img2;
        ImageView img3;

        PersonalRecordViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cardview);
            nickname = (TextView)itemView.findViewById(R.id.cvNickname);
            time = (TextView)itemView.findViewById(R.id.cvTime);
            difficulty = (TextView) itemView.findViewById(R.id.cvDifficulty);

            img1 = (ImageView) itemView.findViewById(R.id.cvImage1);
            img2 = (ImageView) itemView.findViewById(R.id.cvImage2);
            img3 = (ImageView) itemView.findViewById(R.id.cvImage3);
        }
    }
}
