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

    TextView textView; // 텍스트뷰 형태로 변수 정의 - > 이변수에 할당할 레이아웃에 텍스트가 정의 되어있을것이다.
    Button button; // 버튼 정의 -> 이버튼에 할당할 레이아웃에 버튼이 정의 되어 있을 것이다
    ListView listView;// 리스트뷰 정의 - > 이리스트변수에 할당할 레이아웃의 리스트가 정의 되어 있을 것이다.
    Button testBtn; // 버튼정의 -> 이버튼에 할당할 레이아웃에 버튼이 정의 되어 있을 것이다


    ArrayList arrayList = new ArrayList(); // java 배열리스트 정의 - > 배열변수를 이용하여 값을 적재 할것이다

    Intent otherMoveIintent ; // 안드로이드에서 지원하는 인텐트를 이용해 먼가 지시 할것이다.
    ArrayAdapter<String> listAdapter; // 안드로이드에서 지원하는 화면과 변수간에 중간역활을 하는 어뎁터를 변수 정의


    @Override //이 엑티비티가 시작되면 어떤일을 시작할지 정의 한다.
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override// 메인엑티비티가 만들어질때 무엇을 할지 정의 한다 대부분 초기화에 대한 구문을 작성한다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSetting();
    }

    //함수의 인자로 받은 내용을 텍스트뷰에 표현한다.
    public void changeText(String text){
        textView.setText(text);
    }

    //정의된 화면관련 변수에 findViewById 메소드에  레이아웃에 정의된 ID를 인자값으로 변수에 버튼,텍스트와 같은 뷰를 할당 한다.
    public void viewSetting(){

        testBtn = findViewById(R.id.testBtn);
        textView = findViewById(R.id.startText);
        listView = findViewById(R.id.list);


        //버튼과 같은 경우 궅이 변수이용없이 클릭되었을때 무엇을 할지 바로 정의
        findViewById(R.id.button).setOnClickListener(// 클릭되었을때 무엇을 해야 되나 ? 감시기를 넣어줘야 한다.
                getButtonClickListener() // <-- 이 함수를 수행한다. 이함수는 감시기를 리턴해준다.
        );

        findViewById(R.id.addRowBtn).setOnClickListener(// 클릭되었을때 무엇을 해야 되나? 감시기를 넣어줘야 한다.
                getaddRowBtnClickListener() // <-- 이 함수를 수행한다. 이함수는 감시기를 리턴해준다.
        );


        findViewById(R.id.otherWindow).setOnClickListener(// 클릭되었을때 무엇을 해야 되나? 감시기를 넣어줘야 한다.
                getOtherWindowClickListener() // <-- 이 함수를 수행한다. 이함수는 감시기를 리턴해준다.
        );

        otherMoveIintent = new Intent(getApplicationContext(),Main2Activity.class);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(listAdapter);

    }

    // 아래 버튼클릭 감지기 3개는 앞서위의 셋온클릭 리스너에  할당되었다.

    //버튼클릭 수행 감지기
    private View.OnClickListener getButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeText("Yea :) Hellow !!");
            }
        };
    }

    //버튼클릭 수행 감지기
    private View.OnClickListener getButtonClick111Listener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeText("Yea :) 111111 !!");
            }
        };
    }

    //버튼클릭 수행 감지기
    private View.OnClickListener getaddRowBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRow(); // 줄을 추가 하는 기능을 수행하도록 되어있는것으로 보아  이감지기는 줄추가 버튼에 할달될것 같다.
            }
        };
    }

    //버튼클릭 수행 감지기
    public View.OnClickListener getOtherWindowClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.super.startActivity(otherMoveIintent); // 인텐트를 인자로 하는것으로 먼가 범위 밖의 프로그램 수행이 예상된다.

            }
        };
    }



    //어뎁터에 값을 가진 줄을 추가 한다.
    public void addRow(){
        for (int i = 0 ; i < 10 ; i++) {
            Map item = new HashMap();
            arrayList.add(textView.getText().toString());
        }

        listAdapter.notifyDataSetChanged();
    }



}
