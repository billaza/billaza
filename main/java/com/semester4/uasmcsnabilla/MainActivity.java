package com.semester4.uasmcsnabilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class MainActivity extends AppCompatActivity {

    public static final String SELECTED_WORD = "selected.txt";

    EditText search_box;
    String word;
    ImageButton search_button;
    Button save_button;
    RecyclerView rv_exp;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_box = findViewById(R.id.search_box);
        search_button = findViewById(R.id.search_button);
        rv_exp = findViewById(R.id.rv_exp);
        save_button = findViewById(R.id.save_button);

        recyclerAdapter = new RecyclerAdapter(MainActivity.this, this);
        rv_exp.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = search_box.getText().toString();
                Call<List<WordResult>> wordList = ApiClient.getWordApi().getAllWords(word);

                wordList.enqueue(new Callback<List<WordResult>>() {
                    @Override
                    public void onResponse(Call<List<WordResult>> call, Response<List<WordResult>> response) {
                        if(response.isSuccessful()){
                            List<WordResult> wordResults = response.body();
                            recyclerAdapter.setData(wordResults);
                            rv_exp.setAdapter(recyclerAdapter);

                            recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent preview = new Intent(MainActivity.this, Preview.class);
                                    String selectedWord = wordResults.get(position).getWord();
                                    preview.putExtra(SELECTED_WORD, selectedWord);
                                    startActivity(preview);
                                }

                                @Override
                                public void onSaveClick(int position) {
                                    String selectedWord = wordResults.get(position).getWord();
                                    DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
                                    Cursor cursor = myDB.readWord(selectedWord);
                                    if(cursor.getCount() == 0) {
                                        myDB.saveWord(selectedWord);
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "This word is already in your favorites.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<WordResult>> call, Throwable t) {
                        Log.e("failure", t.getLocalizedMessage());
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorites) {
            Intent favorites = new Intent(this, Favorites.class);
            startActivity(favorites);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}