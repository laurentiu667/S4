package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Vector;

public class layouttest extends AppCompatActivity {

    TableLayout table;
    TableRow child;
    Button lebutton;
    EditText mdp;
    LinearLayout big_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouttest);

        Ecouteur ec = new Ecouteur();
        table = findViewById(R.id.table_layout);

        mdp = findViewById(R.id.secret);
        big_container = findViewById(R.id.big_container);

        for (int i = 0; i < table.getChildCount(); i++) {
            child = (TableRow) table.getChildAt(i);

            for (int j = 0; j < child.getChildCount(); j++) {
                lebutton = (Button) child.getChildAt(j);
                lebutton.setOnClickListener(ec);
            }
        }
    }

    private class Ecouteur implements View.OnClickListener{
        private final int CODE_SECRET = 667;
        @Override
        public void onClick(View v){
            Button b = (Button) v;
            String concat = mdp.getText().toString() + b.getText().toString();
            mdp.setText(concat);
            if (concat.equals("667")){
                big_container.setBackgroundColor(0xFF00FF00);
            } else {
                big_container.setBackgroundColor(0xFFFF0000);
            }

        }

    }
}