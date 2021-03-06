package com.example.apka_vol4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CurrencyActivityCAD extends AppCompatActivity {
    private EditText minValueCad;
    private EditText maxValueCad;
    private Button saveButton;
    private TextView value;

    private DatabaseReference mDatabase;
    private DatabaseReference valueCAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_cad);

        minValueCad = (EditText) findViewById(R.id.et_min);
        maxValueCad = (EditText) findViewById(R.id.et_max);
        saveButton = (Button) findViewById(R.id.button_save);
        value = (TextView) findViewById(R.id.tv_value);

        mDatabase = FirebaseDatabase.getInstance().getReference("Currencies").child("CAD");
        valueCAD = mDatabase.child("real");

        valueCAD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueCad = dataSnapshot.getValue().toString();
                value.setText(valueCad  + " " + "PLN");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minValueCAD = minValueCad.getText().toString();
                String maxValueCAD = maxValueCad.getText().toString();

                Map newPost = new HashMap();
                newPost.put("min", minValueCAD);
                newPost.put("max", maxValueCAD);

                mDatabase.child("min").setValue(minValueCAD);
                mDatabase.child("max").setValue(maxValueCAD);
            }
        });
    }

}
