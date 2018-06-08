package com.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;




import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import utills.Constant;

public class CustomPostRequest extends AsyncTask<String, Void , String> implements OnKeyListener{

	private String mBaseURL;
	private JSONObject mData;
	private IHttpRequestCallBack mHttpCallBack;

	public CustomPostRequest(IHttpRequestCallBack aHttpRequestCallBack, String aBaseUrl,JSONObject aData) {
		super();
		mHttpCallBack = aHttpRequestCallBack;
		mBaseURL = aBaseUrl;
		mData = aData;
	}

	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		
		String responseStr = null;
		HttpResponse response =null;
		try {
  			    if(mData!=null)
			    {
			    HttpPost httppost = new HttpPost(mBaseURL);
		        StringEntity se = new StringEntity(mData.toString());
		        se.setContentEncoding("UTF-8");
		        se.setContentType("application/json");
		        httppost.setEntity(se);
		        response = httpclient.execute(httppost);
			    }
			    else
			    {
			    	HttpGet httpget=new HttpGet(mBaseURL);
			    	response=httpclient.execute(httpget);
			    }
		        responseStr = EntityUtils.toString(response.getEntity());
		       Log.i("1234", responseStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return Constant.DO_IN_BACKGROUND_EXCEPTION;
		} catch (IOException e) {
			e.printStackTrace();
			return Constant.DO_IN_BACKGROUND_EXCEPTION;
		}

		return responseStr;
	}
///////////////////
	@Override
	protected void onPreExecute() {

		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String s) {
	
		try {
			if (s != null && !(s.equals(Constant.DO_IN_BACKGROUND_EXCEPTION))) {
				try {
					mHttpCallBack.requestSuccessful(s);
					this.cancel(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				mHttpCallBack.requestFailure();
				this.cancel(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==event.KEYCODE_BACK)
		{
			this.cancel(true);
		}
		return false;
	}
}
