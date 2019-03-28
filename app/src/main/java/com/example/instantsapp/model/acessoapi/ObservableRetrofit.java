package com.example.instantsapp.model.acessoapi;

import com.example.instantsapp.model.modelodados.InfoUsuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.instantsapp.Constantes.*;

public class ObservableRetrofit {
    private static Retrofit retrofit = null;

    public static Retrofit getCliente() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(URL_BASE)
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static Observable<List<InfoUsuarios>> getClienteComParametros(Retrofit objetoRetrofit, String login, String senha) {

        RequestBody requestBodyLogin = RequestBody.create(MediaType.parse("text/plain"), login);
        RequestBody requestBodySenha = RequestBody.create(MediaType.parse("text/plain"), senha);

        return objetoRetrofit
                .create(ParametrosApi.class)
                .getValidacaoUsuario(requestBodyLogin,requestBodySenha);
    }

    public static Observable<List<InfoUsuarios>> getListaUsuarios(Retrofit objetoRetrofit) {
        return objetoRetrofit.create(ParametrosApi.class).getListaUsuarios();
    }
}

