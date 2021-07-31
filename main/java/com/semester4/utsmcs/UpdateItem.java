package com.semester4.utsmcs;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class UpdateItem extends AppCompatActivity {

    EditText updateNama, updateNominal;
    Button updateButton;
    TextView updateDate;
    String id, name, date, cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        updateNama = findViewById(R.id.updateNama);
        updateDate = findViewById(R.id.date_display);
        updateNominal = findViewById(R.id.updateNominal);
        updateButton = findViewById(R.id.buttonUpdate);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateItem.this);
                name = updateNama.getText().toString();
                cost = updateNominal.getText().toString();

                int check = 0;
                if(TextUtils.isEmpty(updateNama.getText().toString())) {
                    updateNama.setError("Please enter item name!");
                    check = check + 1;
                }
                if(TextUtils.isEmpty(updateNominal.getText().toString())) {
                    updateNominal.setError("Please enter total cost!");
                    check = check + 1;
                }
                if(check == 0) {
                    myDB.updateItem(id, name, date, cost);
                }
                if(check == 0) finish();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("date")
                && getIntent().hasExtra("cost")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            cost = getIntent().getStringExtra("cost");

            updateNama.setText(name);
            updateNominal.setText(cost);
            Log.d("stev", name + " " + date + " " + cost + " ");
        }
        else {
            Toast.makeText(this, "No item.", Toast.LENGTH_SHORT).show();
        }
    }
}
