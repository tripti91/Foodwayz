package utills;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Thumbnails;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Utils {
    public static ProgressDialog progress_dialog;
    public static DatePickerDialog fromDatePickerDialog;
    public static SimpleDateFormat dateFormatter;
    public static String Date = "";

    public static ProgressDialog ProgressDailog(Activity activity) {
        if (progress_dialog == null) {
            progress_dialog = new ProgressDialog(activity);
        }
        progress_dialog.setMessage("Please wait...");
        progress_dialog.setCancelable(true);
        progress_dialog.setCanceledOnTouchOutside(false);
        return progress_dialog;
    }
    public static boolean is_ValidNumber(EditText aEditText) {
        String text = aEditText.getText().toString().trim();
        boolean valid = false;
        if (text.isEmpty()) {
            aEditText.setError("Please enter Number");
        } else {
            if (text.length() < 10) {
                aEditText.setError("Too short Number");
            } else {
                if (text.length() > 10) {
                    aEditText.setError("Too long Number");
                } else {
                    valid = true;
                }
            }

        }
        return valid;
    }

    public static int phone_count(Context aContext) {
        int count = 0;
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor people = aContext.getContentResolver().query(uri, projection, null, null, null);
        if (null != people && people.getCount() > 0) {
            count = people.getCount();
        }
        return count;
    }

    public static boolean isNetworkAvailable(Activity aActivity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) aActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static AlertDialog.Builder ShowDialog(int id, final Activity context, String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setIcon(android.R.drawable.ic_dialog_alert);
        switch (id) {
            case Constant.NETWORK_FAILED:
                dlgAlert.setMessage("Network not available.");

                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            case Constant.PLEASE_FILL_REQUIRED_DETAIL:
                dlgAlert.setMessage("Please fill required detail.");

                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            case Constant.APP_EXIT:
                dlgAlert.setMessage("Do you want to exit from application.");

                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                });
                break;
            case Constant.SERVER_MESSAGE:
                dlgAlert.setMessage(message);

                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }


        dlgAlert.setCancelable(false);
        dlgAlert.create().show();

        return dlgAlert;

    }
}
