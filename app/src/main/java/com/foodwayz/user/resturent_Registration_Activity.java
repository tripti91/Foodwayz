package com.foodwayz.user;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.foodwayz.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.network.CustomPostRequest;
import com.network.IHttpRequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import Modles.UserData;
import utills.Constant;
import utills.Utils;

/**
 * Created by Tripti on 25/06/2017.
 */
public class resturent_Registration_Activity extends AppCompatActivity implements View.OnClickListener, IHttpRequestCallBack {
    public static boolean mStatus;
    public static String mMessage;
    private TextView txt_sign_in, txt_sign_in_resto;
    private EditText etEmail, etPassword, etname, etconfirm_pass, etmobile;
    private Button btn_sign_up;
    private ProgressDialog progress_dialog = null;
    Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_resturent);
        ct = this;
        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(this);
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        findElements();
    }

    private void findElements() {
        txt_sign_in_resto = (TextView) findViewById(R.id.txt_sign_in_resto);
        txt_sign_in = (TextView) findViewById(R.id.txt_sign_in);
        txt_sign_in.setOnClickListener(this);
        etconfirm_pass = (EditText) findViewById(R.id.etComfirm_pass);
        etname = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etmobile = (EditText) findViewById(R.id.etmobile);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(this);
       // txt_sign_in_resto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_sign_in:
                Intent intent = new Intent(resturent_Registration_Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_forgot_pass:
                break;
            case R.id.btn_sign_up:
                StartProcess();
                break;
            case R.id.txt_sign_in_resto:

                break;
        }
    }

    private void Add_Restorent() {
    }

    private void StartProcess() {
        if (Utils.isNetworkAvailable(resturent_Registration_Activity.this)) {
            boolean IsFormValid = true;
            if (etname.getText().toString().trim().length() <= 3) {
                etname.setError("Name should be minimum 4 character");
                IsFormValid = false;
            }
            if (etPassword.getText().toString().length() < 6 && etPassword.getText().toString().length() > 15) {
                etPassword.setError("Please enter password between 6 to 15 character");
                IsFormValid = false;
            }
            if (etconfirm_pass.getText().toString().length() < 6 && etconfirm_pass.getText().toString().length() > 15) {
                etconfirm_pass.setError("Please enter password between 6 to 15 character");
                IsFormValid = false;
            }
            if (!Utils.is_ValidNumber(etmobile)) {
                etmobile.setError("Please enter valid number");
                IsFormValid = false;
            }
            if (!etconfirm_pass.getText().toString().equals(etPassword.getText().toString())) {
                etconfirm_pass.setError("Password & confirm password do not match");
                IsFormValid = false;
            }

            if (IsFormValid) {
               // startHttpRequest();
                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_popup);
                TextView phone_no = (TextView) dialog.findViewById(R.id.tv_phone);
                phone_no.setText(etmobile.getText().toString());
                dialog.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Verification.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        } else {
            Utils.ShowDialog(Constant.NETWORK_FAILED, this, Constant.MSG_INTERNET_CONNECTION_FAILURE);
        }
    }

    private void startHttpRequest() {
        showProgressDialog();
        JSONObject jsonObject = getJsonObject();
        CustomPostRequest httpRequest = new CustomPostRequest(resturent_Registration_Activity.this, getResources().getString(R.string.URL_REGISTER), jsonObject);
        httpRequest.execute();
    }


    private JSONObject getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Name", etname.getText().toString().trim());
            jsonObject.put("Mobile", etmobile.getText().toString().trim());
            jsonObject.put("Password", etPassword.getText().toString().trim());
            jsonObject.put("ConfirmPassword", etconfirm_pass.getText().toString().trim());
            jsonObject.put("Role", "User");
            jsonObject.put("UserType", "1");

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
    public void requestSuccessful(String aDataStr) throws JSONException {
        hideProgressDialog();

        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String

        UserData emp1 = gson.fromJson(aDataStr, UserData.class);

        // print object data
        System.out.println("\n\nEmployee Object\n\n" + emp1);

        // create JSON String from Object
        System.out.print(emp1.getData().getEmail());

     /*    JSONObject jsonObject = new JSONObject(aDataStr);
       JsonParser.parseDataUser(aDataStr);
        if (jsonObject.has(Constant.JSON_STATUS_TAG))
            mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
        if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
            mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
        }
//        JsonParser.parseDataUserPrivacy(aDataStr);
        if (mStatus) {
            Toast.makeText(Registration_Activity.this, mMessage, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Verification.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }, 2 * 1000); // wait for 2 seconds
        } else {
            Toast.makeText(Registration_Activity.this, mMessage, Toast.LENGTH_SHORT).show();
        }
*/
    }

    @Override
    public void requestFailure() {
        hideProgressDialog();
        showFailedAlertBox("Try again....");
    }
}
