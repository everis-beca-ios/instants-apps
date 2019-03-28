package com.example.instantsapp.model.acessoapi;

import com.example.instantsapp.model.modelodados.InfoUsuarios;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ParametrosApi {

    @Multipart
    @POST("login")
    Observable<List<InfoUsuarios>> getValidacaoUsuario(@Part("id") RequestBody login, @Part("passwd") RequestBody password);

    @GET("users")
    Observable<List<InfoUsuarios>> getListaUsuarios();
}
