package Modles;

import java.util.ArrayList;

/**
 * Created by Prateek on 7/15/2017.
 */
public class Resturent_detail {
    String id;
ArrayList<String> location= new ArrayList<>();

    Float Rateing;
    String resturent_name;
    String area;
    String city;
    String rateing_count;

    public Float getRateing() {
        return Rateing;
    }



    public void setRateing(Float rateing) {
        Rateing = rateing;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;
    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getResturent_name() {
        return resturent_name;
    }

    public void setResturent_name(String resturent_name) {
        this.resturent_name = resturent_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRateing_count() {
        return rateing_count;
    }

    public void setRateing_count(String rateing_count) {
        this.rateing_count = rateing_count;
    }


}
