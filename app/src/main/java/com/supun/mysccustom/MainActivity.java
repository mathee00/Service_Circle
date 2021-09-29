package com.supun.mysccustom;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//Created by S.M.Suriyaarachchi
//IT20187514

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> worker_id, worker_name, worker_description, worker_skill, worker_rating;
    CustomAdapter customAdapter;


    String s1[],s2[];
    int images[] = {R.drawable.a,R.drawable.m,R.drawable.a,R.drawable.m,R.drawable.a,
            R.drawable.m,R.drawable.a,R.drawable.a,R.drawable.m,R.drawable.a};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        worker_id = new ArrayList<>();
        worker_name = new ArrayList<>();
        worker_description = new ArrayList<>();
        worker_skill = new ArrayList<>();
        worker_rating = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,worker_id,worker_name,
                worker_description,worker_skill,worker_rating, images);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0 ) {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                worker_id.add(cursor.getString(0));
                worker_name.add(cursor.getString(1));
                worker_description.add(cursor.getString(2));
                worker_skill.add(cursor.getString(3));
                worker_rating.add(cursor.getString(4));
            }
        }
    }
}