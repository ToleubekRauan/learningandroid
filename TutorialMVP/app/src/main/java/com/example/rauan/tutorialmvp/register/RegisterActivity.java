package com.example.rauan.tutorialmvp.register;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.rauan.tutorialmvp.R;
import com.google.gson.JsonObject;

public class RegisterActivity extends MvpAppCompatActivity implements RegisterView {

    private EditText etEmail, etPassword, etFullname;
    private Button btnRegister;
    private TextView tvRes;

    ProgressDialog mProgressDialog;

    @InjectPresenter
    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = (EditText) findViewById(R.id.regEmail);
        etPassword = (EditText) findViewById(R.id.regPassword);
        etFullname = (EditText) findViewById(R.id.regFullname);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvRes = (TextView) findViewById(R.id.tvResOut);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.register(etEmail.getText().toString(),etPassword.getText().toString(),
                        etFullname.getText().toString());
            }
        });
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
        tvRes.setText(result.toString());
    }
}
