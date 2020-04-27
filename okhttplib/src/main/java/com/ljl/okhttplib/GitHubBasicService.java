package com.ljl.okhttplib;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

public class GitHubBasicService {
    private GitHubBasicApi gitHubApi;

    GitHubBasicService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
               // .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubApi = retrofit.create(GitHubBasicApi.class);
         Single.just("1")
               .subscribe(new SingleObserver<String>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onSuccess(String value) {

                   }

                   @Override
                   public void onError(Throwable e) {

                   }
               });

    }
}
