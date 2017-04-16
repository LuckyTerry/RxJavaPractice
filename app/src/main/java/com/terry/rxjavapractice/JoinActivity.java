package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

public class JoinActivity extends AppCompatActivity {
    @BindView(R.id.tv_join)
    TextView mTvJoin;

    private List<Integer> mIntegersToBeJoin;
    private Observable<Integer> mIntegerObservable;
    private List<String> mStringsToBeJoin;
    private Observable<String> mStringObservable;

    private StringBuilder mStringBuilder = new StringBuilder();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);

        mIntegersToBeJoin = new ArrayList<>();
        mIntegersToBeJoin.add(1);
        mIntegersToBeJoin.add(2);
        mIntegersToBeJoin.add(3);
        mIntegersToBeJoin.add(4);
        mIntegersToBeJoin.add(5);
        mIntegersToBeJoin.add(6);
        mIntegersToBeJoin.add(7);
        mIntegersToBeJoin.add(8);
        mIntegersToBeJoin.add(9);
        mIntegerObservable = Observable.fromIterable(mIntegersToBeJoin)
                .zipWith(Observable.interval(0, 1000, TimeUnit.MILLISECONDS), (i, j) -> i);

        mStringsToBeJoin = new ArrayList<>();
        mStringsToBeJoin.add("A");
        mStringsToBeJoin.add("B");
        mStringsToBeJoin.add("C");
        mStringsToBeJoin.add("D");
        mStringsToBeJoin.add("F");
        mStringsToBeJoin.add("G");
        mStringsToBeJoin.add("H");
        mStringsToBeJoin.add("I");
        mStringsToBeJoin.add("J");
        mStringObservable = Observable.fromIterable(mStringsToBeJoin)
                .zipWith(Observable.interval(0, 1000, TimeUnit.MILLISECONDS), (str, i) -> str);

    }

    @OnClick(R.id.btn_join)
    public void onViewClicked() {
        mIntegerObservable.join(mStringObservable,
                i -> Observable.timer(1500, TimeUnit.MILLISECONDS),
                str -> Observable.timer(1500, TimeUnit.MILLISECONDS),
                (i, str) -> i + str)
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (str, i) -> str)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> mTvJoin.setText(mStringBuilder.append(str + "\n")));
    }
}
