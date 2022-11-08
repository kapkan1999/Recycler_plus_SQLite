package com.example.recycler_plus_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

//Все, что наследуется от активити, должно в названии это ясно содержать, переименовал
public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, email, age;
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        //Не уверен, что можно (и нужно) таким образом сохранять DBHelper, немного почитай про утечки памяти с context
        DB = new DBHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        //Вот здесь тебе лучше тоже не передавать контекст в адаптер
        adapter = new MyAdapter( this, name, email, age);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    // А вот так делать низя, ты в бд лезешь из Main потока. Пока некритично, но это неправильно
    private void displaydata() {
        Cursor cursor = DB.getData();

        //Code style поправил, он общепринятный
        if (cursor.getCount() == 0) {
            //Вроде можно просто this в контекст передавать
            Toast.makeText(this,"No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                age.add(cursor.getString(2));
            }
        }
    }
}