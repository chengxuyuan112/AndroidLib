package com.ljl.myrxjajva;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxTest {
    private Disposable disposable;
    public static void main(String[] args) {

    }
    private void  SingInit(){
        Single.just("aaa").map(new Function<String, String>() {

            @Override
            public String apply(String s) throws Exception {
                return null;
            }
        }) .subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable =d;
            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
    private void MapInit(){
        Single.just("1").map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s);
            }
        }).subscribe();
    }
}
