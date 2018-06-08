package Modles;

/**
 * Created by Tripti on 01/10/2017.
 */
public class Native_item {String mTitle;
    String mSubtitle;
    int mIcon;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public void setmSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public Native_item(String title, int icon) {
        mTitle = title;
        mIcon = icon;

    }

}
