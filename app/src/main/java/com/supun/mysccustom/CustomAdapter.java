package com.supun.mysccustom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Created by S.M.Suriyaarachchi
//IT20187514


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList worker_id, worker_name, worker_description, worker_skill, worker_rating;
    String data1[], data2[];
    String name_view_data[], description_view_data[], skill_view_data[], rating_view_data[];
    int images[];
    int position;

    //The Adapters' constructor
    CustomAdapter(Context context, ArrayList worker_id,
                  ArrayList worker_name,
                  ArrayList worker_description,
                  ArrayList worker_skill,
                  ArrayList worker_rating,
                  int imgs[]) {
        this.context = context;
        this.worker_id = worker_id;
        this.worker_name = worker_name;
        this.worker_description = worker_description;
        this.worker_skill = worker_skill;
        this.worker_rating = worker_rating;
        this.images = imgs;

        String[] tempData1 = new String[worker_name.size()];
        worker_name.toArray(tempData1);
        this.name_view_data = tempData1;

        String[] tempData2 = new String[worker_description.size()];
        worker_name.toArray(tempData2);
        this.description_view_data = tempData2;

        String[] tempData3 = new String[worker_skill.size()];
        worker_name.toArray(tempData3);
        this.skill_view_data = tempData3;

        String[] tempData4 = new String[worker_skill.size()];
        worker_name.toArray(tempData4);
        this.rating_view_data = tempData4;

    }

    @NonNull
    @Override
    //This method creates and initializes the view holder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new CustomAdapter.MyViewHolder(view);
    }

    @Override
    //This method binds the data to the view holder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.worker_name_txt.setText(String.valueOf(worker_name.get(position)));
        holder.worker_description_txt.setText(String.valueOf(worker_description.get(position)));
        holder.worker_skill_txt.setText(String.valueOf(worker_skill.get(position)));
        holder.worker_rating_txt.setText(String.valueOf(worker_rating.get(position)));
        holder.worker_id_txt.setText(String.valueOf((worker_id.get(position))));
        int imgLocation = position%2 ;
        holder.myImage.setImageResource(images[imgLocation]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            //this method captures the onClick event
            public void onClick(View view) {
                Intent intenet = new Intent(context,SecondActivity.class);
                intenet.putExtra("id",String.valueOf((worker_id.get(position))));
                intenet.putExtra("name",String.valueOf((worker_name.get(position))));
                intenet.putExtra("description",String.valueOf((worker_description.get(position))));
                intenet.putExtra("skill",String.valueOf((worker_skill.get(position))));
                intenet.putExtra("rating",String.valueOf((worker_rating.get(position))));
                intenet.putExtra("myImage",images[imgLocation]);

                context.startActivity(intenet);
            }
        });
    }

    @Override
    //This method counts the individual elements in the recycle view
    public int getItemCount() {
        return worker_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView worker_id_txt, worker_name_txt, worker_description_txt, worker_skill_txt, worker_rating_txt;
        ImageView myImage;
        ConstraintLayout mainLayout;

        //This is the holder class (Sub class of the adapter class)
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            worker_name_txt = itemView.findViewById(R.id.worker_name_txt);
            worker_description_txt = itemView.findViewById(R.id.worker_description_txt);
            worker_skill_txt= itemView.findViewById(R.id.worker_skill_txt);
            worker_rating_txt = itemView.findViewById(R.id.worker_rating_txt);
            worker_id_txt = itemView.findViewById(R.id.worker_id_txt);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
