package com.supun.mysccustom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//Created by S.M.Suriyaarachchi
//IT20187514

public class EditActivity extends AppCompatActivity {
    EditText name_input, description_input, skill_input, rating_input;
    Button edit_button;
    String worker_id;
    String id_edit_data, name_edit_data, description_edit_data, skill_edit_data,rating_edit_data;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name_input = findViewById(R.id.name_input_e);
        description_input = findViewById(R.id.description_input_e);
        skill_input = findViewById(R.id.skill_input_e);
        rating_input = findViewById(R.id.rating_input_e);

        getAndSetIntentData();
        worker_id = id_edit_data;

        edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(EditActivity.this);
                myDB.updateWorker(worker_id,name_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        skill_input.getText().toString().trim(),
                        rating_input.getText().toString().trim());
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name_edit_data);


    }

    //Getting data from user and setting those data in the interface
    void getAndSetIntentData() {
            id_edit_data = getIntent().getStringExtra("id");
            name_edit_data = getIntent().getStringExtra("name");
            description_edit_data = getIntent().getStringExtra("description");
            skill_edit_data = getIntent().getStringExtra("skill");
            rating_edit_data= getIntent().getStringExtra("rating");
            myImage = getIntent().getIntExtra("myImage", 1);

            //Setting intent data
            name_input.setText(name_edit_data);
            description_input.setText(description_edit_data);
            skill_input.setText(skill_edit_data);
            rating_input.setText(rating_edit_data);
    }

}