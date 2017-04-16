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

public class BufferActivity extends AppCompatActivity {

    @BindView(R.id.tv_buffer)
    TextView mTvBuffer;
    @BindView(R.id.tv_buffer_with_skip)
    TextView mTvBufferWithSkip;
    @BindView(R.id.tv_bufferwith_skip_and_timespan)
    TextView mTvBufferwithSkipAndTimespan;

    private List<Integer> mIntegerToBeBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
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

    @OnClick({R.id.btn_start, R.id.btn_start_with_skip, R.id.btn_start_with_skip_and_timespan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Observable.fromIterable(mIntegerToBeBuffer)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .buffer(2)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> mTvBuffer.setText(list.toString()), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                break;
            case R.id.btn_start_with_skip:
                Observable.fromIterable(mIntegerToBeBuffer)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .buffer(3, 2)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> mTvBufferWithSkip.setText(list.toString()), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                break;
            case R.id.btn_start_with_skip_and_timespan:
                Observable.fromIterable(mIntegerToBeBuffer)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .buffer(2, 3, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(list -> mTvBufferwithSkipAndTimespan.setText(list.toString()), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                break;
        }
    }
}
