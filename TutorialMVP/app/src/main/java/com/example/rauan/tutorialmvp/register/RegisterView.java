package com.example.rauan.tutorialmvp.register;

import com.arellomobile.mvp.MvpView;
import com.google.gson.JsonObject;

/**
 * Created by rauan on 11.07.17.
 */

public interface RegisterView extends MvpView {

    void showLoading();

    void hideLoading();

    void showError(String error);

    void showResult(JsonObject result);
}
