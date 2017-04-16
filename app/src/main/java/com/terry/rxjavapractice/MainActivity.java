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


    @OnClick({R.id.btn_interval, R.id.btn_lambda, R.id.btn_scan, R.id.btn_group, R.id.btn_buffe,
            R.id.btn_window, R.id.btn_cast, R.id.btn_merge, R.id.btn_zip, R.id.btn_join,
            R.id.btn_combine_latest})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_interval:
                intent = new Intent(MainActivity.this, IntervalActivity.class);
                break;
            case R.id.btn_lambda:
                intent = new Intent(MainActivity.this, LambdaActivity.class);
                break;
            case R.id.btn_scan:
                intent = new Intent(MainActivity.this, ScanActivity.class);
                break;
            case R.id.btn_group:
                intent = new Intent(MainActivity.this, GroupByActivity.class);
                break;
            case R.id.btn_buffe:
                intent = new Intent(MainActivity.this, BufferActivity.class);
                break;
            case R.id.btn_window:
                intent = new Intent(MainActivity.this, WindowActivity.class);
                break;
            case R.id.btn_cast:
                intent = new Intent(MainActivity.this, CastActivity.class);
                break;
            case R.id.btn_merge:
                intent = new Intent(MainActivity.this, MergeActivity.class);
                break;
            case R.id.btn_zip:
                intent = new Intent(MainActivity.this, ZipActivity.class);
                break;
            case R.id.btn_join:
                intent = new Intent(MainActivity.this, JoinActivity.class);
                break;
            case R.id.btn_combine_latest:
                intent = new Intent(MainActivity.this, CombineLatestActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
