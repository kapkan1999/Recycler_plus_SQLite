package com.example.recycler_plus_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Перед переменными лучше пробел оставить

    //Стиль кода поправил
    EditText name, email, age;
    Button insert, view;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.Email);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.btnInsert);
        //Название для переменной лучше нужно подобрать
        view = findViewById(R.id.btnView);

        db = new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String ageTXT = age.getText().toString();

                //Поменял название
                //Данные в бд кладешь в Main потоке, так низя
                Boolean isInsertSuccess = db.insertUserData(nameTXT, emailTXT, ageTXT);

                if (isInsertSuccess) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}