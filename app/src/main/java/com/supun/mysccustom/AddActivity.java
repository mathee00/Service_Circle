package com.supun.mysccustom;

//Created by S.M.Suriyaarachchi
//IT20187514

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText name_input, description_input, skill_input, rating_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        description_input = findViewById(R.id.description_input);
        skill_input = findViewById(R.id.skill_input);
        rating_input = findViewById(R.id.rating_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){

            //The function of the Add button
            //When the Add button is clicked, below onClick method will be invoked
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                //Add Worker, validation (All the fields should be filled)
                if((name_input.getText().length() ==0)  ||
                        (description_input.getText().length() ==0)  ||
                        (skill_input.getText().length() ==0)  ||
                        (rating_input.getText().length() ==0)) {
                    Toast.makeText(AddActivity.this,"Please enter all the information",Toast.LENGTH_SHORT).show();
                } else {


                    myDB.addWorker(name_input.getText().toString().trim(),
                            description_input.getText().toString().trim(),
                            skill_input.getText().toString().trim(),
                            rating_input.getText().toString().trim());

                    //After clicking the Add button, this class will navigated to MainActivity
                    Intent i = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}