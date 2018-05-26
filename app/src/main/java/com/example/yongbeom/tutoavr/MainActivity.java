package com.example.yongbeom.tutoavr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    ListView listView;

    ArrayList arrayList = new ArrayList();

    Intent otherMoveIintent ;
    ArrayAdapter<String> listAdapter;

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSetting();
    }

    public void changeText(String text){
        textView.setText(text);
    }

    public void viewSetting(){
        textView = findViewById(R.id.startText);
        listView = findViewById(R.id.list);
        findViewById(R.id.button).setOnClickListener(getButtonClickListener());
        findViewById(R.id.addRowBtn).setOnClickListener(getaddRowBtnClickListener());
        findViewById(R.id.otherWindow).setOnClickListener(getOtherWindowClickListener());
        otherMoveIintent = new Intent(getApplicationContext(),Main2Activity.class);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(listAdapter);

    }

    private View.OnClickListener getButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeText("Yea :) Hellow !!");
            }
        };
    }

    private View.OnClickListener getaddRowBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRow();
            }
        };
    }

    public void addRow(){
        for (int i = 0 ; i < 10 ; i++) {
            Map item = new HashMap();
            arrayList.add(textView.getText().toString());
        }

        listAdapter.notifyDataSetChanged();
    }


    public View.OnClickListener getOtherWindowClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.super.startActivity(otherMoveIintent);

            }
        };
    }
}
