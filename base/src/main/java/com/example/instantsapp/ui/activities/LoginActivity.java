package com.example.instantsapp.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instantsapp.Constantes;
import com.example.instantsapp.R;
import com.example.instantsapp.viewmodel.BecariosViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etPass;
    private EditText etLogin;
    private Button btLogar;
    private BecariosViewModel viewModel;
    private MutableLiveData<Constantes.Resposta> resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = ViewModelProviders
                .of(this).get(BecariosViewModel.class);

        etPass = findViewById(R.id.et_password);
        etLogin = findViewById(R.id.et_login);
        btLogar = findViewById(R.id.bt_logar);
        btLogar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                validarELancarTelaDetalhes();
            }
        });
    }

    private void validarELancarTelaDetalhes() {
        if(usuarioEhValido(etLogin.getText().toString()) || senhaEhValida(etPass.getText().toString())) {
            Intent intencao = new Intent(this,ListaBecaActivity.class);
            intencao.putExtra("nome_usuario_logado",etLogin.getText().toString());
            startActivity(intencao);
        } else {
            Toast.makeText(this,"Nome de usuario e senha precisam ter ao menos 5 caracteres!",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean usuarioEhValido(String usuario) {
        return usuario.length() > 4;
    }

    private boolean senhaEhValida(String password) {
        return password.length() > 4;
    }


}

