package com.terry.rxjavapractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.btn_join})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(MainActivity.this, IntervalActivity.class);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this, LambdaActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this, ScanActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(MainActivity.this, GroupByActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(MainActivity.this, BufferActivity.class);
                break;
            case R.id.button6:
                intent = new Intent(MainActivity.this, WindowActivity.class);
                break;
            case R.id.button7:
                intent = new Intent(MainActivity.this, CastActivity.class);
                break;
            case R.id.btn_join:
                intent = new Intent(MainActivity.this, JoinActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
