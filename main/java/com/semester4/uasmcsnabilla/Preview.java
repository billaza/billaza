package com.semester4.uasmcsnabilla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class Preview extends AppCompatActivity {

    public static final String SELECTED_WORD = "selected.txt";

    TextView selected_word;
    Button save_button_prev;
    RecyclerView rv_preview;
    RecyclerAdapter3 recyclerAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        setTitle("");

        selected_word = findViewById(R.id.selected_word);
        save_button_prev = findViewById(R.id.save_button_prev);
        rv_preview = findViewById(R.id.rv_preview);

        recyclerAdapter3 = new RecyclerAdapter3(Preview.this, this);
        rv_preview.setLayoutManager(new LinearLayoutManager(Preview.this));

        Intent intent = getIntent();
        String selectedWord = intent.getStringExtra(SELECTED_WORD);

        Call<List<WordResult>> wordList = ApiClient.getWordApi().getAllWords(selectedWord);

        wordList.enqueue(new Callback<List<WordResult>>() {
            @Override
            public void onResponse(Call<List<WordResult>> call, Response<List<WordResult>> response) {
                if (response.isSuccessful()) {
                    List<WordResult> wordResults = response.body();
                    recyclerAdapter3.setData(wordResults);
                    rv_preview.setAdapter(recyclerAdapter3);
                    selected_word.setText(wordResults.get(recyclerAdapter3.position).getWord());
                }
            }

            @Override
            public void onFailure(Call<List<WordResult>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

        save_button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(Preview.this);
                Cursor cursor = myDB.readWord(selectedWord);
                if (cursor.getCount() == 0) {
                    myDB.saveWord(selectedWord);
                } else {
                    Toast.makeText(Preview.this, "This word is already in your favorites.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}