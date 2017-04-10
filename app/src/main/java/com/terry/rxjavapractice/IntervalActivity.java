package com.terry.rxjavapractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IntervalActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView mTextView;

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button9, R.id.button10,R.id.button11, R.id.button12, R.id.button13})
    public void onViewClicked(View view) {
        Observable<Long> observable = null;
        switch (view.getId()) {
            case R.id.button9:
                observable = Observable.interval(1, TimeUnit.SECONDS);
                break;
            case R.id.button10:
                observable = Observable.interval(0, 1, TimeUnit.SECONDS);
                break;
            case R.id.button11:
                observable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.computation());
                break;
            case R.id.button12:
                observable = Observable.interval(0, 1, TimeUnit.SECONDS, Schedulers.computation());
                break;
            case R.id.button13:
                if (mDisposable != null) {
                    if (!mDisposable.isDisposed()) {
                        mDisposable.dispose();
                        mDisposable = null;
                        mTextView.setText("");
                    }
                }
                break;
        }
        if (observable != null) {
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(Long aLong) {
                            mTextView.setText(aLong + "");
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            if (!mDisposable.isDisposed()) {
                mDisposable.dispose();
                mDisposable = null;
            }
        }
    }
}
