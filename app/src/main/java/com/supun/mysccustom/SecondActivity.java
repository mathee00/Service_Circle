package com.supun.mysccustom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//Created by S.M.Suriyaarachchi
//IT20187514

public class SecondActivity extends AppCompatActivity {
    ImageView mainImageView;
    TextView id, name,description, skill, rating;
    Button edit_button, delete_button;

    String data1,data2;
    String id_view_data, name_view_data, description_view_data, skill_view_data,rating_view_data;
    int myImage;

    private ArrayList worker_id, worker_name, worker_description, worker_skill, worker_rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.mainImageView);
        name = findViewById(R.id.name_txt_view);
        description = findViewById(R.id.description_txt_view);
        skill= findViewById(R.id.skill_txt_view);
        rating = findViewById(R.id.rating_txt_view);
        id = findViewById(R.id.id_txt_view);

        getData();
        setData();
        edit_button = findViewById(R.id.edit_button);

        //Creating an onClickListener for the edit button
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, EditActivity.class);
                intent.putExtra("id",String.valueOf(id.getText()));
                intent.putExtra("name",String.valueOf(name.getText()));
                intent.putExtra("description",String.valueOf(description.getText()));
                intent.putExtra("skill",String.valueOf(skill.getText()));
                intent.putExtra("rating",String.valueOf(rating.getText()));
                startActivity(intent);
            }
        });

        delete_button = findViewById(R.id.delete_button);

        //Creating an onClickListener for the delete button
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    private void getData() {
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("id") &&
        getIntent().hasExtra("name")){
            id_view_data = getIntent().getStringExtra("id");
            name_view_data = getIntent().getStringExtra("name");
            description_view_data = getIntent().getStringExtra("description");
            skill_view_data = getIntent().getStringExtra("skill");
            rating_view_data= getIntent().getStringExtra("rating");
            myImage = getIntent().getIntExtra("myImage", 1);
        } else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }

    }

    private void setData() {
        id.setText(id_view_data );
        name.setText(name_view_data );
        description.setText(description_view_data );
        skill.setText(skill_view_data);
        rating.setText(rating_view_data);
        mainImageView.setImageResource(myImage);
    }

    //This the method pops up the alert dialog box for deleting a worker
    void confirmDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete " + name_view_data + " ?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(SecondActivity.this);
                myDB.deleteWorker(id_view_data);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialogBuilder.create().show();
    }
}