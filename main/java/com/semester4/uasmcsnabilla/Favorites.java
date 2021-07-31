package com.semester4.uasmcsnabilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class Favorites extends AppCompatActivity {

    public static final String SELECTED_WORD = "selected.txt";

    DatabaseHelper myDB = new DatabaseHelper(Favorites.this);
    ArrayList<String> id;
    ArrayList<String> saved_word;

    Button delete_button;
    RecyclerView rv_fav;
    RecyclerAdapter2 recyclerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setTitle("Favorites");

        id = new ArrayList<>();
        saved_word = new ArrayList<>();
        delete_button = findViewById(R.id.delete_button);
        rv_fav = findViewById(R.id.rv_fav);

        recyclerAdapter2 = new RecyclerAdapter2(Favorites.this, this, id, saved_word);
        rv_fav.setLayoutManager(new LinearLayoutManager(Favorites.this));
        rv_fav.setAdapter(recyclerAdapter2);

        showWords();

        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent preview = new Intent(Favorites.this, Preview.class);
                String selectedWord = saved_word.get(position);
                preview.putExtra(SELECTED_WORD, selectedWord);
                startActivity(preview);
            }

            @Override
            public void onSaveClick(int position) {
                DatabaseHelper myDB = new DatabaseHelper(Favorites.this);
                myDB.deleteWord(id.get(position), saved_word.get(position));
            }
        });
    }

    void showWords() {
        Cursor cursor = myDB.readAllWords();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No item.", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()) {
                id.add(cursor.getString(0));
                saved_word.add(cursor.getString(1));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            Intent intent = new Intent(this, Favorites.class);
            finish();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}