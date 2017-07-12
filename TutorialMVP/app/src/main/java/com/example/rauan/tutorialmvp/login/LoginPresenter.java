package com.example.rauan.tutorialmvp.login;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.rauan.tutorialmvp.network.RestClient;
import com.google.gson.JsonObject;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rauan on 10.07.17.
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>{

    private Subscription mSubscription;


    public void login(String email, String password){

        if(TextUtils.isEmpty(email)){
            getViewState().showError("Empty email");
            return;
        }
        if(TextUtils.isEmpty(password)){
            getViewState().showError("Empty password");
            return;
        }
        getViewState().showLoading();
        mSubscription = RestClient.request().login(email,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideLoading();
                        getViewState().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        getViewState().hideLoading();
                        getViewState().showResult(jsonObject);
                    }
                });

    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
