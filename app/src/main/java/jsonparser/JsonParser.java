package jsonparser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import Modles.Citydetail;
import Modles.Resturent_detail;
import Modles.SuggestGetSet;
import utills.Constant;
import utills.SessionManager;

/**
 * Created by Tripti on 27/06/2017.
 */
public class JsonParser {
    public static boolean mStatus;
    public static String mMessage;
    public static String Data;
    public static int mId;
    SessionManager session;

    public JsonParser() {
    }

    public static void parseDataUserPrivacy(String parser) {
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");

            JSONObject jsonObject = new JSONObject(parser);

            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
            }
            if (jsonObject.has(Constant.JSON_DATA_TAG)) {
                JSONObject dataObject = jsonObject.optJSONObject(Constant.JSON_DATA_TAG);
                if (null != dataObject) {
                    int Userid = dataObject.getInt(Constant.JSON_USER_ID);
                    String name = dataObject.getString(Constant.JSON_NAME_TAG);
                    String Mobile_no = dataObject.getString(Constant.JSON_MOBILE_NO_TAG);
                    String Role = dataObject.getString(Constant.JSON_ROLE_TAG);
                    String token = dataObject.getString(Constant.JSON_TOKEN_TAG);
                    boolean mobile_status = dataObject.getBoolean(Constant.JSON_MOBILESTATUS_TAG);
                    SessionManager.setUserDetail(Userid, name, Mobile_no, Role, token, mobile_status);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseDataUser(String parser) {
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");

            JSONObject jsonObject = new JSONObject(parser);

            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
            }
            if (jsonObject.has(Constant.JSON_DATA_TAG)) {
                JSONObject dataObject = jsonObject.optJSONObject(Constant.JSON_DATA_TAG);
                if (null != dataObject) {
                    int Userid = dataObject.getInt(Constant.JSON_USER_ID);
                    String name = dataObject.getString(Constant.JSON_NAME_TAG);
                    String Mobile_no = dataObject.getString(Constant.JSON_MOBILE_NO_TAG);
                    SessionManager.setUserDetail(Userid, name, Mobile_no, "", "",true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Resturent_detail>parsseResturentlist (String parser) {
    ArrayList<String> location =new ArrayList<>();
        ArrayList<Resturent_detail> response =new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");
            jsonObject = new JSONObject(parser);
            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
                if (jsonObject.has(Constant.JSON_MESSAGE_TAG))
                    mId = jsonObject.getInt(Constant.JSON_APP_ID_TAG);
            }
                if (jsonObject.has(Constant.JSON_DATA_TAG)) {

                    JSONArray datObject = jsonObject.getJSONArray(Constant.JSON_DATA_TAG);
                    if (null != datObject) {
                        for (int i = 0; i < datObject.length(); i++) {
                            Resturent_detail add_data=new Resturent_detail();
                            JSONObject json_country_list = datObject.getJSONObject(i);
                            String name = json_country_list.getString(Constant.JSON_NAME_TAG);
                            String Address = json_country_list.getString("Address");
                            String TagLine = json_country_list.getString("TagLine");
                            String Photo = json_country_list.getString("Photo");
                            String Slug = json_country_list.getString("Slug");
                            String City = json_country_list.getString("City");
                            String TotalRating = json_country_list.getString("TotalRating");
                            JSONArray Locations = json_country_list.getJSONArray("Locations");
                            for (int i1 = 0; i1 < Locations.length(); i1++) {
                                String Locations_ = Locations.getString(i1);
                                Log.i("..........", "" + Locations_);
                                // loop and add it to array or arraylist
                                location.add(Locations_);
                            }
                            add_data.setResturent_name(name);
                            add_data.setAddress(Address);
                            add_data.setArea(Slug);
                            add_data.setRateing(Float.parseFloat(TotalRating));
                            add_data.setLocation(location);
                            response.add(add_data);
                        }

                    }

                }

        } catch (Exception e) {//aNKIT
            // TODO: handle exception
        } finally {
            jsonObject = null;
        }
        return response;
    }

    public static ArrayList<Citydetail> parseCountry(String parser) {

        ArrayList<Citydetail> reponse = new ArrayList<Citydetail>();
        JSONObject jsonObject = null;
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");
            jsonObject = new JSONObject(parser);
            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
                if (jsonObject.has(Constant.JSON_MESSAGE_TAG))
                    mId = jsonObject.getInt(Constant.JSON_APP_ID_TAG);

                if (jsonObject.has(Constant.JSON_DATA_TAG)) {
                    JSONArray datObject = jsonObject.getJSONArray(Constant.JSON_DATA_TAG);
                    if (null != datObject) {
                        for (int i = 0; i < datObject.length(); i++) {
                            Citydetail add_data = new Citydetail();
                            JSONObject json_country_list = datObject.getJSONObject(i);

                            if (json_country_list.has("Id")) {
                                int id = 0;
                                try {
                                    id = json_country_list
                                            .getInt("Id");
                                } catch (Exception e) {
                                    // TODO: handle exception
                                }
                                add_data.setId(id);
                            }
                            if (json_country_list.has("Name")) {
                                String name = "";
                                name = json_country_list
                                        .getString("Name");
                                if (null != name && name.length() > 0 && !name.trim().equalsIgnoreCase("null")) {
                                    add_data.setCity_name(name);
                                } else
                                    add_data.setCity_name("");
                            }
                            reponse.add(add_data);
                        }
                    }
                }

            }
        } catch (Exception e) {//aNKIT
            // TODO: handle exception
        } finally {
            jsonObject = null;
        }
        return reponse;
    }

    public List<SuggestGetSet> getParseJsonWCF(String sName, String city) {
        List<SuggestGetSet> ListData = new ArrayList<SuggestGetSet>();
        try {
            String temp = sName.replace(" ", "%20");
            String citys = city;
            URL js = new URL("http://abc.itractechnology.com/api/Location/GetLocations/citys/" + temp);
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            JSONObject jsonResponse = new JSONObject(line);
            JSONArray jsonArray = jsonResponse.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject r = jsonArray.getJSONObject(i);
                ListData.add(new SuggestGetSet(r.getString("id"), r.getString("name")));
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return ListData;

    }
/*

    public static ArrayList<Resturent_detail> parsseResturentlist(String parser) {
        ArrayList<Citydetail> reponse = new ArrayList<Citydetail>();
        JSONObject jsonObject = null;
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");
            jsonObject = new JSONObject(parser);
            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);
                if (jsonObject.has(Constant.JSON_MESSAGE_TAG))
                    mId = jsonObject.getInt(Constant.JSON_APP_ID_TAG);

                if (jsonObject.has(Constant.JSON_DATA_TAG)) {
                    JSONArray datObject = jsonObject.getJSONArray(Constant.JSON_DATA_TAG);
                    if (null != datObject) {
                        for (int i = 0; i < datObject.length(); i++) {
                            Resturent_detail add_data = new Resturent_detail();
                            JSONObject json_country_list = datObject.getJSONObject(i);

                            if (json_country_list.has("Name")) {
                                String name = "";
                                name = json_country_list
                                        .getString("Name");
                                if (null != name && name.length() > 0 && !name.trim().equalsIgnoreCase("null")) {
                                    add_data.setResturent_name(name);
                                } else
                                    add_data.setResturent_name("");
                            }
                            if (json_country_list.has("Name")) {
                                String name = "";
                                name = json_country_list
                                        .getString("Name");
                                if (null != name && name.length() > 0 && !name.trim().equalsIgnoreCase("null")) {
                                    add_data.setResturent_name(name);
                                } else
                                    add_data.setResturent_name("");
                            }
                            reponse.add(add_data);
                        }
                    }
                }

            }
        } catch (Exception e) {//aNKIT
            // TODO: handle exception
        } finally {
            jsonObject = null;
        }
        return reponse;
    }
*/

   /* public static void parseDataVerification(String parser) {
        try {
            if (parser.contains("\"{"))
                parser = parser.replace("\"{", "{");
            if (parser.contains("\\"))
                parser = parser.replace("\\", "");

            JSONObject jsonObject = new JSONObject(parser);

            if (jsonObject.has(Constant.JSON_STATUS_TAG))
                mStatus = jsonObject.getBoolean(Constant.JSON_STATUS_TAG);
            if (jsonObject.has(Constant.JSON_MESSAGE_TAG)) {
                mMessage = jsonObject.getString(Constant.JSON_MESSAGE_TAG);

            }
            if (jsonObject.has(Constant.JSON_DATA_TAG)) {
                JSONArray jsonArray = jsonObject.optJSONArray(Constant.JSON_DATA_TAG);
                if (null != jsonArray && jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonUserSettingdata = jsonArray.getJSONObject(i);
                        if (jsonUserSettingdata.has("id")) {
                            int Userid = jsonUserSettingdata.getInt("id");
                            String name = jsonUserSettingdata.getString(Constant.JSON_NAME_TAG);
                            String Mobile_no = jsonUserSettingdata.getString(Constant.JSON_MOBILE_NO_TAG);
                            String User_type = jsonUserSettingdata.getString(Constant.JSON_USER_TYPE_TAG);
                            String VerificationCode = jsonUserSettingdata.getString(Constant.JSON_ROLE_TAG);
                            String RestaurantId = jsonUserSettingdata.getString(Constant.JSON_RESTURENT_ID_TAG);
                            //              SessionManager.setUserDetail(Userid, name,"", Mobile_no, User_type, VerificationCode, RestaurantId);

                        }

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
