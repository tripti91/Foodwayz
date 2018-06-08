package com.foodwayz.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.foodwayz.R;
import com.network.CustomPostRequest;
import com.network.IHttpRequestCallBack;

import org.json.JSONObject;

import jsonparser.JsonParser;
import utills.Constant;
import utills.SessionManager;
import utills.Utils;

/**
 * Created by Tripti on 25/06/2017.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener ,IHttpRequestCallBack{
    private EditText etEmail, etPassword;
    private Button btn_login;
    private ImageButton btnGPlus;
    private Context ct;
    private ImageButton btnFb;
    SessionManager session;
    private TextView txt_sign_up, txt_forgot_pass;
    private ProgressDialog progress_dialog = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lyt);
        ct = this;
        session = new SessionManager(this);
        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(this);
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        findElement();
    }

    private void findElement() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        txt_forgot_pass = (TextView) findViewById(R.id.txt_forgot_pass);
        txt_forgot_pass.setOnClickListener(this);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        txt_sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_sign_up:
                Intent intent = new Intent(LoginActivity.this, Registration_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_forgot_pass:
                break;
            case R.id.btn_login:

   StartProcess();
                break;
        }
    }

    private void StartProcess() {
        if (Utils.isNetworkAvailable(LoginActivity.this)) {
            boolean IsFormValid = true;

            if (etPassword.getText().toString().length() < 6 && etPassword.getText().toString().length() > 15) {
                etPassword.setError("Please enter password between 6 to 15 character");
                IsFormValid = false;
            }
            if (!Utils.is_ValidNumber(etEmail)) {
                etEmail.setError("Please enter valid number");
                IsFormValid = false;
            }
            if (IsFormValid) {
                if(etEmail.getText().toString().equals("8602713660")) {
                    SessionManager.setuserRole("admin");
                    Intent in = new Intent(LoginActivity.this, com.foodwayz.owner.HomeActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                }else
                {
                    SessionManager.setuserRole("user");
                    Intent in = new Intent(LoginActivity.this, HomeActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                }

               // startHttpRequest();
            }
        } else {
            Utils.ShowDialog(Constant.NETWORK_FAILED, this, Constant.MSG_INTERNET_CONNECTION_FAILURE);
        }
    }

    private void startHttpRequest() {
        showProgressDialog();
        JSONObject jsonObject = getJsonObject();
        CustomPostRequest httpRequest = new CustomPostRequest(LoginActivity.this, getResources().getString(R.string.URL_LOGIN), jsonObject);
        httpRequest.execute();
    }

    private JSONObject getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Mobile", etEmail.getText().toString().trim());
            jsonObject.put("Password", etPassword.getText().toString().trim());

        } catch (Exception e) {
            // TODO: handle exception
        }
        return jsonObject;
    }

    public void showProgressDialog() {

        if (!progress_dialog.isShowing()) {
            progress_dialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progress_dialog.isShowing()) {
            progress_dialog.dismiss();
        }
    }

    private void showFailedAlertBox(String aMsg) {
        new AlertDialog.Builder(ct)
                .setMessage(aMsg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }




    @Override
    public void requestSuccessful(String aDataStr) {
        hideProgressDialog();
        System.out.println("aDataStr--------->"+aDataStr);
        JsonParser.parseDataUserPrivacy(aDataStr);
        if (JsonParser.mStatus) {
            if (!session.getMobile_status().get(Constant.SHARED_PREFERENCE_MOBILE_STATUS_KEY)) {
                Intent intent = new Intent(getApplicationContext(), Verification.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {

                if (session.getRole().get(Constant.SHARED_PREFERENCE_ROLE_KEY).equals("User")) {
                    session.setIsEntered();
                    Intent in = new Intent(LoginActivity.this, HomeActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                    finish();
                } else {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        } else {


            Toast.makeText(LoginActivity.this, aDataStr, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(LoginActivity.this, aDataStr, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void requestFailure() {
        hideProgressDialog();
        showFailedAlertBox("Try again....");
    }
}
