package com.example.rauan.tutorialmvp.login;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.rauan.tutorialmvp.R;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    private EditText etEmail, etPassword;
    private Button btnEnter;
    private TextView tvResult;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
    }

    @Override
    public void showLoading() {
        if (mProgressDialog != null){
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null){
            mProgressDialog.hide();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(JsonObject result) {
        tvResult.setText(result.toString());
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }
}
