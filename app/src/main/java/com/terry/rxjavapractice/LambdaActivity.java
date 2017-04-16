package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by tengchengwei on 2017/4/15.
 */

public class LambdaActivity extends AppCompatActivity {
    @BindView(R.id.tv_msg)
    TextView mTvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create)
    public void onViewClicked() {
        Observable.just("lambda")
                .subscribe(s -> mTvMsg.setText(s));
    }
}
