package com.foodwayz.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodwayz.R;
import com.network.CustomPostRequest;
import com.network.IHttpRequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import jsonparser.JsonParser;
import utills.Constant;
import utills.SessionManager;

/**
 * Created by Tripti on 27/06/2017.
 */
public class Verification extends Activity implements View.OnClickListener, IHttpRequestCallBack {
    EditText ed_phoneverification;
    Button submit_verification, resend_verification;
    ProgressDialog progress_dialog;
    SessionManager session;
    String mobile_no;
    Context ct;
    ImageView iv_back;
    TextView call_me,Sms_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);
        session = new SessionManager(this);
        ct = this;
        mobile_no = session.getMobile_no().get(Constant.SHARED_PREFERENCE_MOBILE_NUMBER_KEY);

        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(this);
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        findElement();
    }

    private void findElement() {
        Sms_number = (TextView)findViewById(R.id.Sms_number);
        call_me = (TextView)findViewById(R.id.call_me);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        ed_phoneverification = (EditText) findViewById(R.id.ed_phoneverification);
        submit_verification = (Button) findViewById(R.id.submit_verification);
        submit_verification.setOnClickListener(this);
        resend_verification = (Button) findViewById(R.id.resend_verification);
        resend_verification.setOnClickListener(this);
        Sms_number.setText(getResources().getString(R.string.We_have_sent)+mobile_no);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_verification:
                if (!(ed_phoneverification.getText().toString().length() == 0)) {
                    startHttpRequest();
                }
                break;
            case R.id.resend_verification:
                startHttpRequest_resend();
                break;
            case R.id.iv_back:
                Intent in =new Intent(Verification.this,Registration_Activity.class);
                startActivity(in);
                finish();
                break;

            case R.id.call_me:

                break;

        }
    }

    private void startHttpRequest() {
        showProgressDialog();

        JSONObject objJson = new JSONObject();

        String code = ed_phoneverification.getText().toString();
        CustomPostRequest httpRequest = new CustomPostRequest(Verification.this, getResources().getString(R.string.URL_VERIFICATION) + "?mobile=" + mobile_no + "&code=" + code, objJson);
        httpRequest.execute();
    }

    private void startHttpRequest_resend() {
        showProgressDialog();
        JSONObject objJson = new JSONObject();
        CustomPostRequest httpRequest = new CustomPostRequest(Verification.this, getResources().getString(R.string.URL_Resend) + "?mobile=" + mobile_no, objJson);
        httpRequest.execute();
    }


    private JSONObject getJsonObject_1() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", session.getMobile_no().get(Constant.SHARED_PREFERENCE_MOBILE_NUMBER_KEY));

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

    @Override
    public void requestSuccessful(String aDataStr) {

        hideProgressDialog();
        JSONObject jsonObject = null;
        Boolean mStatus;
        String mMessage = null;
        int mType = 0;
        try {
            jsonObject = new JSONObject(aDataStr);
            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
            }
            if (jsonObject.has(Constant.JSON_APP_ID_TAG)) {
                mType = jsonObject.getInt(Constant.JSON_APP_ID_TAG);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mType == Constant.Verification_api_id) {
            if (JsonParser.mStatus)
                session.setIsEntered();
            session.setMobileStatus(JsonParser.mStatus);
            if (session.getRole().get(Constant.SHARED_PREFERENCE_ROLE_KEY).equals("User")) {
                Intent in = new Intent(Verification.this, HomeActivity.class);
                startActivity(in);
                finish();

            } else {
                Intent in = new Intent(Verification.this, HomeActivity.class);
                startActivity(in);
                finish();
            }
        }

        if (mType == Constant.Resend_api_id) {
            if (JsonParser.mStatus) {
                showFailedAlertBox(mMessage);

            }
        }
    }


    @Override
    public void requestFailure() {

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

}