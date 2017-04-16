package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.terry.rxjavapractice.util.RxUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by tengchengwei on 2017/4/16.
 */

public class CastActivity extends AppCompatActivity {
    @BindView(R.id.tv_cast)
    TextView mTvCast;

    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_cast)
    public void onViewClicked() {
        // 更多是检验数据类型合法，如果非法则抛出cast异常
        Observable.just(0.5, 1.01, 10.2, 4.6, 1.35)
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i, j) -> i)
                .cast(Double.class)
                .compose(RxUtils.applySchedulers())
                .subscribe(str -> mTvCast.setText(mStringBuilder.append(str + "\n")));

    }
}
