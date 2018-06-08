package com.network;

import android.os.Bundle;

import org.json.JSONException;

public interface IHttpRequestCallBack {


    public void requestSuccessful(String aDataStr) throws JSONException;
    public void requestFailure();


}
