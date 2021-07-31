package com.semester4.utsmcs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class NewItem extends AppCompatActivity {

    EditText addName, addNominal;
    String addDate;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        addName = findViewById(R.id.inputNama);
        addNominal = findViewById(R.id.inputNominal);
        addButton = findViewById(R.id.buttonAdd);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(NewItem.this);
                int check = 0;
                if(TextUtils.isEmpty(addName.getText().toString())) {
                    addName.setError("Please enter item name!");
                    check = check + 1;
                }
                if(TextUtils.isEmpty(addNominal.getText().toString())) {
                    addNominal.setError("Please enter total cost!");
                    check = check + 1;
                }

                if(check == 0) {
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);
                    if(month <= 8) addDate = year + "-0" + (month + 1) + "-" + day;
                    else addDate = year + "-" + (month + 1) + "-" + day;

                    double temp2 = Double.parseDouble(addNominal.getText().toString());
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    String finalNominal = "Rp" + formatter.format(temp2);
                    myDB.addItem(addName.getText().toString(), addDate, finalNominal);
                }
                if(check == 0) finish();
            }
        });

    }
}
