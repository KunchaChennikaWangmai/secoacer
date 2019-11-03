package com.example.secondacer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public String mn;
    TextView tv;
    Button bt;
Button bt2;
EditText et;
String get;
public String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tt);
        bt=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        et=(EditText)findViewById(R.id.editText);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             get=et.getText().toString();


            }
        });
        Toast.makeText(this,get,Toast.LENGTH_LONG).show();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(get);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        value = dataSnapshot.getValue(String.class);


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                        mn="Failed to read value.";
                    }

                });
                tv.setText("added to Firebase("+value+")");}
        });

    }


}
