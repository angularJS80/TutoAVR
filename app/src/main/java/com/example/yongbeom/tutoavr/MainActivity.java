package com.example.yongbeom.tutoavr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView; // 텍스트뷰 형태로 변수 정의 - > 이변수에 할당할 레이아웃에 텍스트가 정의 되어있을것이다.
    ListView listView;// 리스트뷰 정의 - > 이리스트변수에 할당할 레이아웃의 리스트가 정의 되어 있을 것이다.


    Intent otherMoveIintent ; // 안드로이드에서 지원하는 인텐트를 이용해 먼가 지시 할것이다.

    Intent moveLocationIntent;
    ArrayAdapter<String> listAdapter; // 안드로이드에서 지원하는 화면과 변수간에 중간역활을 하는 어뎁터를 변수 정의

    ArrayList arrayList = new ArrayList(); // java 배열리스트 정의 - > 배열변수를 이용하여 값을 적재 할것이다
    View.OnClickListener buttonClickListener;
    View.OnClickListener addRowBtnClickListener;
    View.OnClickListener otherWindowClickListener;
    View.OnClickListener locationWindowClickListener;


    @Override //이 엑티비티가 시작되면 어떤일을 시작할지 정의 한다.
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override// 메인엑티비티가 만들어질때 무엇을 할지 정의 한다 대부분 초기화에 대한 구문을 작성한다.
    protected void onCreate(Bundle savedInstanceState) {
        // 엑티비티 작성시 자동생성 라인  엑티비티 수행을 위해 상위 클레스에서 제공하는 onCreate 수행
        super.onCreate(savedInstanceState);

        // 엑티비티 작성시 자동생성 라인 레이아웃의 자원을 현재 엑티비티에서 사용되도록 세팅
        setContentView(R.layout.activity_main);
        init(); // 개발자 정의 함수 수행

    }

    private void init() {
        viewDefine();
        procSetting(); // 뷰영역과 분리 가능한 영역에 대한 코딩
        defienListener(); // 정의만 가능한 영역에 대한 코딩
        viewSetting(); // 뷰에 대한 세팅 + 뷰에 정의를 메핑
        viewProcSetting(); // 리스트뷰와 같은 어뎁터가 사용되는것은 뷰와 처리 분리가 어려운것들에 대한 셋팅

    }

    private void viewDefine() {
        textView = findViewById(R.id.startText);
        listView = findViewById(R.id.list);
        // 버튼 자체 변화 (ex 버튼색상변경 등) 이 필요하면 버튼도 정의 해야 함.
    }


    //정의된 화면관련 변수에 findViewById 메소드에  레이아웃에 정의된 ID를 인자값으로 변수에 버튼,텍스트와 같은 뷰를 할당 한다.
        public void viewSetting(){

            settingBtn();

        }

        public void procSetting(){

            HashMap paramPap = new HashMap();
                paramPap.put("username","아무개");
                paramPap.put("userage","3");
            otherMoveIintent = MakeIntent(paramPap,Main2Activity.class);
            moveLocationIntent = MakeIntent(paramPap,LocationActivity.class);
        }

        // 버튼들에게 수신기를 달아준다.
        private void settingBtn() {
            // 커스텀 함수를 수행하여 버튼에 대한 수신기를 달아준다.
            MakeBtnListener(R.id.button, buttonClickListener); // 여기서 R.id.button 중 button 은 팔레트에서 레이아웃 작성시 자동으로 설정된 버튼의 id
            MakeBtnListener(R.id.moveLocation, otherWindowClickListener);
            MakeBtnListener(R.id.moveLocation, locationWindowClickListener);
            MakeBtnListener(R.id.addRowBtn, addRowBtnClickListener);
        }

        //버튼 등에 들아줄 수신기를 정의 한다.
        private void defienListener() {
            buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeText("Yea :) Hellow !!");
                }
            };

            addRowBtnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addRow(); // 줄을 추가 하는 기능을 수행하도록 되어있는것으로 보아  이감지기는 줄추가 버튼에 할달될것 같다.
                }
            };

            otherWindowClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.super.startActivity(otherMoveIintent); // 인텐트를 인자로 하는것으로 먼가 범위 밖의 프로그램 수행이 예상된다.

                }
            };

            locationWindowClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.super.startActivity(moveLocationIntent); // 인텐트를 인자로 하는것으로 먼가 범위 밖의 프로그램 수행이 예상된다.

                }
            };

        }

        private void viewProcSetting() {
            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter);

        }



    // 코드 간소화를 위해 안드로이드 findViewById 를 통해 버튼을 정의 하는 함수를 별도로 만듬.
    private void MakeBtnListener(int inputBtn, View.OnClickListener inputOnClickListener) {
        findViewById(inputBtn).setOnClickListener(// 어떤 버튼이 클릭되었을때 무엇을 해야 되나? 감시기를 넣어줘야 한다.
                inputOnClickListener // <-- 이 함수를 수행한다. 이함수는 감시기를 리턴해준다.
        );
    }


    public Intent MakeIntent(HashMap paramMap,Class toContextClass){
        Intent rtnIntent = new Intent(getApplicationContext(),toContextClass);
        rtnIntent.putExtra("fromActivity","Main2Activity");
        rtnIntent.putExtra("paramMap",paramMap);
        return rtnIntent;
    }

    //함수의 인자로 받은 내용을 텍스트뷰에 표현한다.
    public void changeText(String text){
        textView.setText(text);
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
