package Component;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.foodwayz.R;

/**
 * @author Hardik A Bhalodi
 */
public class MyFontButton extends Button {

	private static final String TAG = "TextView";

	private Typeface typeface;

	public MyFontButton(Context context) {
		super(context);
	}

	public MyFontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
	}

	public MyFontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	private void setCustomFont(Context ctx, AttributeSet attrs) {
		TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.app);
		String customFont = a.getString(R.styleable.app_customFont);
		setCustomFont(ctx, customFont);
		a.recycle();
	}

	private boolean setCustomFont(Context ctx, String asset) {
		try {
			if (typeface == null) {
//				Log.i(TAG, "asset:: " + "fonts/" + asset);
				typeface = Typeface.createFromAsset(ctx.getAssets(),
						"fonts/LotoRegular.ttf");
			}

		} catch (Exception e) {
			e.printStackTrace();
//			Log.e(TAG, "Could not get typeface: " + e.getMessage());
			return false;
		}

		setTypeface(typeface);
		return true;
	}

}