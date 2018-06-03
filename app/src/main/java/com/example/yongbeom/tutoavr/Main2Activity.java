package com.example.yongbeom.tutoavr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
interfaceTodoFn blefnImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


       HashMap map = (HashMap)getIntent().getExtras().get("paramMap");
        Log.d("paramMap",map.toString());
        Toast.makeText(this, (String)map.get("username"), Toast.LENGTH_SHORT).show();


        // 동일한 형태(인터페이스 로 구현된 설계서의 인스턴스는 아래와 같이 해당 형태 의 변수에 할당이 가능하다.
        this.blefnImp = new Ble2Activity();
        this.blefnImp = new BleActivity();
        // 구현한 클레스의 버전을 변경하여도 동일하게 적용되고 , 인터페이스에 추가 구성 함수가 있을때 해당하는 인터페이스로 구현된 설계서에 추가 구현해야 된다.



        blefnImp.getGattBleObject();
        blefnImp.searchBleUnitList();
        blefnImp.connectBleUnit();


    }
}
