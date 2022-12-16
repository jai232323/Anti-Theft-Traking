package com.example.antitest_traking.Module;

public class ComplaintsData {

     String UserImageUrl,useridkey,userid,UserName,UserPhoneNumber,UserAddress,MissingItem;


    public ComplaintsData(){

    }

    public ComplaintsData(String userImageUrl, String useridkey, String userid, String userName, String userPhoneNumber, String userAddress, String missingItems) {
        UserImageUrl = userImageUrl;
        this.useridkey = useridkey;
        this.userid = userid;
        UserName = userName;
        UserPhoneNumber = userPhoneNumber;
        UserAddress = userAddress;
        MissingItem = missingItems;
    }

    public String getUserImageUrl() {
        return UserImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        UserImageUrl = userImageUrl;
    }

    public String getUseridkey() {
        return useridkey;
    }

    public void setUseridkey(String useridkey) {
        this.useridkey = useridkey;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getMissingItems() {
        return MissingItem;
    }

    public void setMissingItems(String missingItems) {
        MissingItem = missingItems;
    }
}
