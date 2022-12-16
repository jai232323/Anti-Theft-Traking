package com.example.antitest_traking.Module;

public class VerifiedComplaintsData {

    String UserImageUrl,userid,MissingItem,UserName,UserPhoneNumber,UserAddress,useridkey,ComplaintItemLocation;

    public VerifiedComplaintsData(){

    }

    public VerifiedComplaintsData(String userImageUrl, String userid, String missingItem, String userName, String userPhoneNumber, String userAddress, String useridkey, String complaintItemLocation) {
        UserImageUrl = userImageUrl;
        this.userid = userid;
        MissingItem = missingItem;
        UserName = userName;
        UserPhoneNumber = userPhoneNumber;
        UserAddress = userAddress;
        this.useridkey = useridkey;
        ComplaintItemLocation = complaintItemLocation;
    }

    public String getUserImageUrl() {
        return UserImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        UserImageUrl = userImageUrl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMissingItem() {
        return MissingItem;
    }

    public void setMissingItem(String missingItem) {
        MissingItem = missingItem;
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

    public String getUseridkey() {
        return useridkey;
    }

    public void setUseridkey(String useridkey) {
        this.useridkey = useridkey;
    }

    public String getComplaintItemLocation() {
        return ComplaintItemLocation;
    }

    public void setComplaintItemLocation(String complaintItemLocation) {
        ComplaintItemLocation = complaintItemLocation;
    }
}
