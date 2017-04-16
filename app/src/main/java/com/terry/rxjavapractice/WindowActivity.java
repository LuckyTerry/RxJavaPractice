package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by tengchengwei on 2017/4/16.
 */

public class WindowActivity extends AppCompatActivity {
    @BindView(R.id.tv_window)
    TextView mTvWindow;
    @BindView(R.id.tv_window_with_skip)
    TextView mTvWindowWithSkip;

    private List<Integer> mIntegerToBeBuffer;

    private static final String TAG = "WindowActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        ButterKnife.bind(this);

        mIntegerToBeBuffer = new ArrayList<>();
        mIntegerToBeBuffer.add(0);
        mIntegerToBeBuffer.add(1);
        mIntegerToBeBuffer.add(2);
        mIntegerToBeBuffer.add(3);
        mIntegerToBeBuffer.add(4);
        mIntegerToBeBuffer.add(5);
        mIntegerToBeBuffer.add(6);
        mIntegerToBeBuffer.add(7);
        mIntegerToBeBuffer.add(8);
        mIntegerToBeBuffer.add(9);
    }

    @OnClick({R.id.btn_start, R.id.btn_start_with_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Observable.fromIterable(mIntegerToBeBuffer)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .window(3, TimeUnit.SECONDS)
                        .subscribe(o -> {
                                    o.observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(i -> {
                                                if (i % 3 == 0) {
                                                    Toast.makeText(this, "window " + i / 3, Toast.LENGTH_SHORT).show();
                                                }
                                                mTvWindow.setText("" + i);
                                            });
                                }
                        );
                break;
            case R.id.btn_start_with_skip:
                Observable.fromIterable(mIntegerToBeBuffer)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .window(2, 3)
                        .subscribe(o -> o.observeOn(AndroidSchedulers.mainThread()).subscribe(i -> mTvWindowWithSkip.setText("" + i)));
                break;
        }
    }
}
