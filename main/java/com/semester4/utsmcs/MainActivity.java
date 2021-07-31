package com.semester4.utsmcs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_spend;
    DatabaseHelper myDB;
    ArrayList<String> item_id, item_name, date, item_cost;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_spend = findViewById(R.id.rv_spend);
        myDB = new DatabaseHelper(MainActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        date = new ArrayList<>();
        item_cost = new ArrayList<>();
        storeData();

        recyclerAdapter = new RecyclerAdapter(MainActivity.this, this, item_id, item_name, date, item_cost);
        rv_spend.setAdapter(recyclerAdapter);
        rv_spend.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent addNew = new Intent(this, NewItem.class);
            startActivity(addNew);
            return true;
        }
        else if (id == R.id.action_refresh) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void storeData() {
        Cursor cursor = myDB.readAllItem();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No item.", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()) {
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                date.add(cursor.getString(2));
                item_cost.add(cursor.getString(3));
            }
        }
    }
}