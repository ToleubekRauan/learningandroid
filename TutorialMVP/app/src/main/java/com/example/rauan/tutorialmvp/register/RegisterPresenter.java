package com.example.rauan.tutorialmvp.register;

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
 * Created by rauan on 11.07.17.
 */
@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterView> {

    private Subscription mSubscription;

    public void register(String email, String password, String fullname){
        mSubscription = RestClient.request().signUp(email, password, fullname)
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
