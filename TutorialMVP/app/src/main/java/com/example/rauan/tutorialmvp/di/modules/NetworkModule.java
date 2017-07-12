package com.example.rauan.tutorialmvp.di.modules;

import com.example.rauan.tutorialmvp.network.ApiConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rauan on 10.07.17.
 */
@Module
public class NetworkModule {

    public static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String providesUrl() {
        return ApiConstants.BASE_URL;
    }

}
