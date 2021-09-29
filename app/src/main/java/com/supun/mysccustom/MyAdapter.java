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

//Created by S.M.Suriyaarachchi
//IT20187514

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;
    public MyAdapter(Context ct, String s1[], String s2[], int imgs[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = imgs;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(String.valueOf(data1[position]));
        holder.myText2.setText(String.valueOf(data2[position]));
        holder.myImage.setImageResource(images[position]);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenet = new Intent(context,SecondActivity.class);
                intenet.putExtra("data1",data1[position]);
                intenet.putExtra("data2",data2[position]);

                intenet.putExtra("myImage",images[position]);
                context.startActivity(intenet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.worker_name_txt);
            myText2 = itemView.findViewById(R.id.worker_description_txt);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
