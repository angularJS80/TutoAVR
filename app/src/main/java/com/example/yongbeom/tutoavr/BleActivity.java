package com.example.yongbeom.tutoavr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BleActivity extends AppCompatActivity implements interfaceTodoFn{ // 해당 작업이 필요한 인터페이스를 받아서 구현해야 될 함수를 구현해야 한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
    }

    @Override
    public void getGattBleObject() {

    }

    @Override
    public void searchBleUnitList() {

    }

    @Override
    public void connectBleUnit() {

    }
}
