package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by tengchengwei on 2017/4/16.
 */

public class MergeActivity extends AppCompatActivity {
    @BindView(R.id.tv_merge)
    TextView mTvMerge;

    private List<String> mStrings1;
    private List<String> mStrings2;
    private Observable<String> mObservable1;
    private Observable<String> mObservable2;
    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        ButterKnife.bind(this);

        mStrings1 = new ArrayList<>();
        mStrings1.add("1");
        mStrings1.add("2");
        mStrings1.add("3");
        mStrings1.add("4");
        mStrings1.add("5");
        mObservable1 = Observable.fromIterable(mStrings1);

        mStrings2 = new ArrayList<>();
        mStrings2.add("A");
        mStrings2.add("B");
        mStrings2.add("C");
        mStrings2.add("D");
        mStrings2.add("E");
        mObservable2 = Observable.fromIterable(mStrings2);
    }

    @OnClick(R.id.btn_start_merge)
    public void onViewClicked() {
        Observable.merge(mObservable1, mObservable2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> mTvMerge.setText(mStringBuilder.append(str + "\n")));
    }
}
