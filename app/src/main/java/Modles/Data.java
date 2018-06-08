package Modles;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Prateek on 9/5/2017.
 */
public class Data {

@SerializedName("UserId")
private int id;
private String Name,Email,MobileNumber,UserType,VerificationCode,RestaurantId;

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", MobileNumber='" + MobileNumber + '\'' +
                ", UserType='" + UserType + '\'' +
                ", VerificationCode='" + VerificationCode + '\'' +
                ", RestaurantId='" + RestaurantId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }
}
