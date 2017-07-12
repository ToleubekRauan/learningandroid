package com.example.rauan.tutorialmvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by rauan on 10.07.17.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {


    public void makeHello(){
        getViewState().showHello("Helo");
    }
}
