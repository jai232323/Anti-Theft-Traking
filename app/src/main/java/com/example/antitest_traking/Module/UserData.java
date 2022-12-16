package com.example.antitest_traking.Module;

public class UserData {
    String UserId,UserName,UserEmail,UserPassword,UserConfirmPassword,UserPhoneNumber,UserAddress,UserImageUrl;
    public UserData() {

    }

    public UserData(String userId, String userName, String userEmail, String userPassword, String userConfirmPassword, String userPhoneNumber, String userAddress, String userImageUrl) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
        UserConfirmPassword = userConfirmPassword;
        UserPhoneNumber = userPhoneNumber;
        UserAddress = userAddress;
        UserImageUrl = userImageUrl;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return UserConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        UserConfirmPassword = userConfirmPassword;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getUserImageUrl() {
        return UserImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        UserImageUrl = userImageUrl;
    }
}
