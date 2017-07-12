package com.example.rauan.tutorialmvp.login;

import com.arellomobile.mvp.MvpView;
import com.google.gson.JsonObject;


/**
 * Created by rauan on 10.07.17.
 */

public interface LoginView extends MvpView {

    void showLoading();

    void hideLoading();

    void showError(String error);

    void showResult(JsonObject result);


}
