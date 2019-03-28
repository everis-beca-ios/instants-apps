package com.example.instantsapp.model.repositorios;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.instantsapp.Constantes.Resposta;
import com.example.instantsapp.model.acessoapi.ObservableRetrofit;
import com.example.instantsapp.model.dblocal.BecariosRoom;
import com.example.instantsapp.model.modelodados.InfoUsuarios;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RepositorioBecarios {
    private final BecariosRoom becariosRoom;
    private final Retrofit objetoRetrofit;

    public RepositorioBecarios(BecariosRoom becariosRoom, Retrofit objetoRetrofit) {
        this.becariosRoom = becariosRoom;
        this.objetoRetrofit = objetoRetrofit;
    }

    public MutableLiveData<Resposta> pegarUsuarioLogado(String login, String senha) {
        final MutableLiveData<Resposta> data = new MutableLiveData<>();
        ObservableRetrofit.getClienteComParametros(objetoRetrofit,login,senha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<InfoUsuarios>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<InfoUsuarios> infoUsuarios) {
                        data.setValue(Resposta.ONNEXT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("erro = [" + e.getMessage() + "]");
                        data.setValue(Resposta.ONERROR);
                    }

                    @Override
                    public void onComplete() {
                        data.setValue(Resposta.ONCOMPLETE);
                    }
                });
        return data;
    }

    public MutableLiveData<Resposta> pegarListaUsuarios() {
        final MutableLiveData<Resposta> data = new MutableLiveData<>();
        ObservableRetrofit.getListaUsuarios(objetoRetrofit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<InfoUsuarios>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<InfoUsuarios> infoUsuarios) {
                        adicionarAoDb(infoUsuarios);
                        data.setValue(Resposta.ONNEXT);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("erro = [" + e.getMessage() + "]");
                        data.setValue(Resposta.ONERROR);
                    }

                    @Override
                    public void onComplete() {
                        data.setValue(Resposta.ONCOMPLETE);
                    }
                });
        return data;
    }

    private void adicionarAoDb(List<InfoUsuarios> listaUsuarios) {
        Single.just(listaUsuarios)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<InfoUsuarios>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<InfoUsuarios> infoUsuarios) {
                        becariosRoom.becariosDao().inserirUsuarios(infoUsuarios);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    public LiveData<List<InfoUsuarios>> pegarUsuarios() {
        return becariosRoom.becariosDao().pegarListaUsuarios();
    }

    public void deletarTudo() {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                becariosRoom.becariosDao().deletarTodosOsUsuarios();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }
}
