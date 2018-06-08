package utills;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.security.Key;
import java.util.HashMap;


public class SessionManager {
    // All Shared Preferences Keys
    public static final String KEY_LOGIN = "login";
    // Sharedpref file name
    private static final String PREF_NAME = "Foodwayz";
    // Editor for Shared preferences
    static Editor editor;
    // Shared Preferences
   public static SharedPreferences pref;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;



    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setUserDetail(int userid, String name, String mobile_no, String role, String token, boolean mobile_status) {
        editor.putInt(Constant.SHARED_PREFERENCE_USER_ID_KEY, userid);
        editor.putString(Constant.SHARED_PREFERENCE_REGISTER_NAME_KEY, name);
        editor.putString(Constant.SHARED_PREFERENCE_ROLE_KEY, role);
        editor.putString(Constant.SHARED_PREFERENCE_MOBILE_NUMBER_KEY, mobile_no);
        editor.putString(Constant.SHARED_PREFERENCE_TOKEN_KEY, token);
        editor.putBoolean(Constant.SHARED_PREFERENCE_MOBILE_STATUS_KEY, mobile_status);
        editor.commit();

    }

    public void setIsEntered() {
        editor.putBoolean(KEY_LOGIN, true);
        editor.commit();
    }

    public HashMap<String, String> getMobile_no() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Constant.SHARED_PREFERENCE_MOBILE_NUMBER_KEY, pref.getString(Constant.SHARED_PREFERENCE_MOBILE_NUMBER_KEY, null));
        return user;
    }
    public HashMap<String, Boolean> getMobile_status() {
        HashMap<String, Boolean> user = new HashMap<String, Boolean>();
        user.put(Constant.SHARED_PREFERENCE_MOBILE_STATUS_KEY, pref.getBoolean(Constant.SHARED_PREFERENCE_MOBILE_STATUS_KEY, false));
        return user;
    }
    public HashMap<String, String> getRole() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Constant.SHARED_PREFERENCE_ROLE_KEY, pref.getString(Constant.SHARED_PREFERENCE_ROLE_KEY, null));
        return user;
    }
    public boolean checkEntered() {
        return pref.getBoolean(KEY_LOGIN, false);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


    }

    public void setMobileStatus(boolean mobileStatus) {
        editor.putBoolean(Constant.SHARED_PREFERENCE_MOBILE_STATUS_KEY, true);
        editor.commit();
    }
    public HashMap<String, String> getusername() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Constant.SHARED_PREFERENCE_REGISTER_NAME_KEY, pref.getString(Constant.SHARED_PREFERENCE_REGISTER_NAME_KEY, null));
        return user;
    }

    public static void setuserRole(String role) {
        editor.putString(Constant.SHARED_PREFERENCE_ROLE, role);
        editor.commit();
    }
    public static HashMap<String, String> getuserRole() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Constant.SHARED_PREFERENCE_ROLE, pref.getString(Constant.SHARED_PREFERENCE_ROLE, ""));
        return user;
    }

    public static void setmyreview(boolean role) {
        editor.putBoolean(Constant.SHARED_PREFERENCE_MYRIVEW, role);
        editor.commit();
    }
    public static boolean getmyreview() {

        return pref.getBoolean(Constant.SHARED_PREFERENCE_MYRIVEW, false);


    }


}