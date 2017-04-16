package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.terry.rxjavapractice.util.RxUtils;

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

public class ScanActivity extends AppCompatActivity {
    @BindView(R.id.tv_item_dig)
    TextView mTvItemDig;
    @BindView(R.id.tv_sum_dig)
    TextView mTvSumDig;
    @BindView(R.id.tv_item_str)
    TextView mTvItemStr;
    @BindView(R.id.tv_sorted_str)
    TextView mTvSortedStr;

    private List<Integer> mIntegerToBeSum;
    private List<String> mNameToBeSorted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        mIntegerToBeSum = new ArrayList<>();
        mIntegerToBeSum.add(1);
        mIntegerToBeSum.add(2);
        mIntegerToBeSum.add(3);
        mIntegerToBeSum.add(4);
        mIntegerToBeSum.add(5);

        mNameToBeSorted = new ArrayList<>();
        mNameToBeSorted.add("12");
        mNameToBeSorted.add("1");
        mNameToBeSorted.add("1234");
        mNameToBeSorted.add("123");
        mNameToBeSorted.add("123456");
        mNameToBeSorted.add("12345");
    }

    @OnClick({R.id.btn_start_sum, R.id.btn_start_sort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_sum:
                // 以下两种方式实现效果一致
//                Observable.fromIterable(mIntegerToBeSum)
//                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnNext(i -> mTvItemDig.setText("" + i))
//                        .scan((sum, item) -> sum + item)
//                        .compose(RxUtils.applySchedulers())
//                        .subscribe(sum -> mTvSumDig.setText("" + sum), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                Observable.zip(Observable.fromIterable(mIntegerToBeSum), Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(i -> mTvItemDig.setText("" + i))
                        .scan((sum, item) -> sum + item)
                        .compose(RxUtils.applySchedulers())
                        .subscribe(sum -> mTvSumDig.setText("" + sum), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                break;
            case R.id.btn_start_sort:
                Observable.fromIterable(mNameToBeSorted)
                        .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (str, j) -> str)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(str -> mTvItemStr.setText(str))
                        .scan((str1, str2) -> str1.length() > str2.length() ? str1 : str2)
                        .distinct()
                        .subscribe(str -> mTvSortedStr.setText(str), e -> e.printStackTrace(), () -> Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show());
                break;
        }
    }
}
