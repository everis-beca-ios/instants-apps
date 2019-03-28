package com.example.instantsapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.instantsapp.Constantes.Resposta;
import com.example.instantsapp.model.acessoapi.ObservableRetrofit;
import com.example.instantsapp.model.dblocal.BecariosRoom;
import com.example.instantsapp.model.modelodados.InfoUsuarios;
import com.example.instantsapp.model.repositorios.RepositorioBecarios;

import java.util.List;

import retrofit2.Retrofit;

import static com.example.instantsapp.Constantes.NOME_DB;

public class BecariosViewModel extends AndroidViewModel {
    private RepositorioBecarios repositorio;

    public List<InfoUsuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<InfoUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    private List<InfoUsuarios> listaUsuarios;

    public BecariosViewModel(@NonNull Application application) {
        super(application);
        iniciarObjetos(application);
    }

    private void iniciarObjetos(Context context) {
        BecariosRoom becariosRoom = Room.databaseBuilder(context,
                BecariosRoom.class, NOME_DB).build();
        Retrofit objRetrofit = ObservableRetrofit.getCliente();
        repositorio = new RepositorioBecarios(becariosRoom, objRetrofit);
    }

    public MutableLiveData<Resposta> pegarUsuarioLogado(String user, String password) {
        return repositorio.pegarUsuarioLogado(user,password);
    }

    public MutableLiveData<Resposta> carregarListaUsuarios() {
        return repositorio.pegarListaUsuarios();
    }

    public LiveData<List<InfoUsuarios>> retornarListaDb() {
        return repositorio.pegarUsuarios();
    }

    public void limparDbUsuarios() {
        repositorio.deletarTudo();
    }


}
