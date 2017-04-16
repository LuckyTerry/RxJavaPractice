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
import io.reactivex.observables.GroupedObservable;

/**
 * Created by tengchengwei on 2017/4/16.
 */

public class GroupByActivity extends AppCompatActivity {
    @BindView(R.id.tv_sort_by_group_by)
    TextView mTvSortByGroupBy;

    private List<String> mNameToBeGroupBy;

    Observable<GroupedObservable<Integer, String>> mGroupedObservableObservable;

    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_by);
        ButterKnife.bind(this);

        mNameToBeGroupBy = new ArrayList<>();
        mNameToBeGroupBy.add("12");
        mNameToBeGroupBy.add("2345");
        mNameToBeGroupBy.add("23");
        mNameToBeGroupBy.add("1");
        mNameToBeGroupBy.add("234");
        mNameToBeGroupBy.add("2");
        mNameToBeGroupBy.add("12345");
        mNameToBeGroupBy.add("123");
        mNameToBeGroupBy.add("23456");
        mNameToBeGroupBy.add("1234");
        mGroupedObservableObservable = Observable.fromIterable(mNameToBeGroupBy)
                .groupBy(str -> str.length());
    }

    @OnClick(R.id.btn_start_group_by)
    public void onViewClicked() {
        Observable.concat(mGroupedObservableObservable)
                .sorted((str1, str2) -> str1.length() < str2.length() ? -1 : 1)
                .subscribe(str -> mTvSortByGroupBy.setText(mStringBuilder.append(str + "\n")));
    }
}
