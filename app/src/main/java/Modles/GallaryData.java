package Modles;

import android.graphics.drawable.Drawable;

/**
 * Created by ADMIN on 06-Feb-18.
 */
public class GallaryData {
    public Integer drawable;
    public boolean isSelected;
public  int id;


    public GallaryData(Integer drawable, boolean isSelected, int id) {
        this.drawable = drawable;
        this.isSelected = isSelected;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

