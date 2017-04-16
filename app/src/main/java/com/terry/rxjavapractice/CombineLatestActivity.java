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
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tengchengwei on 2017/4/16.
 */

public class CombineLatestActivity extends AppCompatActivity {
    @BindView(R.id.tv_combine_latest)
    TextView mTvCombineLatest;

    private List<Integer> mStrings1;
    private List<String> mStrings2;
    private Observable<Integer> mObservable1;
    private Observable<String> mObservable2;
    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_latest);
        ButterKnife.bind(this);

        mStrings1 = new ArrayList<>();
        mStrings1.add(1);
        mStrings1.add(2);
        mStrings1.add(3);
        mStrings1.add(4);
        mStrings1.add(5);
        mObservable1 = Observable.fromIterable(mStrings1)
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (str, i) -> str);

        mStrings2 = new ArrayList<>();
        mStrings2.add("A");
        mStrings2.add("B");
        mStrings2.add("C");
        mStrings2.add("D");
        mStrings2.add("E");
        mStrings2.add("F");
        mObservable2 = Observable.fromIterable(mStrings2)
                .zipWith(Observable.interval(0, 1500, TimeUnit.MILLISECONDS), (str, i) -> str);
    }

    @OnClick(R.id.btn_combine_latest)
    public void onViewClicked() {
        Observable.combineLatest(mObservable1, mObservable2, (i, str) -> i + str)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> mTvCombineLatest.setText(mStringBuilder.append(str + "\n")));
    }
}
