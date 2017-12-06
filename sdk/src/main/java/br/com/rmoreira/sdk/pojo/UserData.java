package br.com.rmoreira.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public class UserData implements Parcelable {

    @SerializedName("_id")
    private String mId;

    @SerializedName("euid")
    private String mEuid;

    @SerializedName("deviceId")
    private String mDeviceId;

    @SerializedName("os")
    private String mOs;

    @SerializedName("osVersion")
    private String mOsVersion;

    @SerializedName("deviceModel")
    private String mDeviceModel;

    @SerializedName("home")
    private String mHome;

    @SerializedName("work")
    private String mWork;

    @SerializedName("istInstalledApps")
    private String mIstInstalledApps;

    @SerializedName("batteryState")
    private Integer mBatteryState;

    @SerializedName("batteryPercentage")
    private Integer mBatteryPercentage;

    @SerializedName("arrival")
    private String mArrival;

    @SerializedName("departure")
    private String mDeparture;

    @SerializedName("categorie")
    private String mCategorie;

    @SerializedName("venueName")
    private String mVenueName;

    @SerializedName("venueTotalTime")
    private Integer mVenueTotalTime;

    @SerializedName("precision")
    private Integer mPrecision;

    @SerializedName("venueLngLat")
    private String mVenueLngLat;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("city")
    private String mCity;

    @SerializedName("state")
    private String mState;

    @SerializedName("country")
    private String mCountry;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEuid() {
        return mEuid;
    }

    public void setEuid(String euid) {
        mEuid = euid;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }

    public String getOs() {
        return mOs;
    }

    public void setOs(String os) {
        mOs = os;
    }

    public String getOsVersion() {
        return mOsVersion;
    }

    public void setOsVersion(String osVersion) {
        mOsVersion = osVersion;
    }

    public String getDeviceModel() {
        return mDeviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        mDeviceModel = deviceModel;
    }

    public String getHome() {
        return mHome;
    }

    public void setHome(String home) {
        mHome = home;
    }

    public String getWork() {
        return mWork;
    }

    public void setWork(String work) {
        mWork = work;
    }

    public String getIstInstalledApps() {
        return mIstInstalledApps;
    }

    public void setIstInstalledApps(String istInstalledApps) {
        mIstInstalledApps = istInstalledApps;
    }

    public Integer getBatteryState() {
        return mBatteryState;
    }

    public void setBatteryState(Integer batteryState) {
        mBatteryState = batteryState;
    }

    public Integer getBatteryPercentage() {
        return mBatteryPercentage;
    }

    public void setBatteryPercentage(Integer batteryPercentage) {
        mBatteryPercentage = batteryPercentage;
    }

    public String getArrival() {
        return mArrival;
    }

    public void setArrival(String arrival) {
        mArrival = arrival;
    }

    public String getDeparture() {
        return mDeparture;
    }

    public void setDeparture(String departure) {
        mDeparture = departure;
    }

    public String getCategorie() {
        return mCategorie;
    }

    public void setCategorie(String categorie) {
        mCategorie = categorie;
    }

    public String getVenueName() {
        return mVenueName;
    }

    public void setVenueName(String venueName) {
        mVenueName = venueName;
    }

    public Integer getVenueTotalTime() {
        return mVenueTotalTime;
    }

    public void setVenueTotalTime(Integer venueTotalTime) {
        mVenueTotalTime = venueTotalTime;
    }

    public Integer getPrecision() {
        return mPrecision;
    }

    public void setPrecision(Integer precision) {
        mPrecision = precision;
    }

    public String getVenueLngLat() {
        return mVenueLngLat;
    }

    public void setVenueLngLat(String venueLngLat) {
        mVenueLngLat = venueLngLat;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mEuid);
        dest.writeString(this.mDeviceId);
        dest.writeString(this.mOs);
        dest.writeString(this.mOsVersion);
        dest.writeString(this.mDeviceModel);
        dest.writeString(this.mHome);
        dest.writeString(this.mWork);
        dest.writeString(this.mIstInstalledApps);
        dest.writeValue(this.mBatteryState);
        dest.writeValue(this.mBatteryPercentage);
        dest.writeString(this.mArrival);
        dest.writeString(this.mDeparture);
        dest.writeString(this.mCategorie);
        dest.writeString(this.mVenueName);
        dest.writeValue(this.mVenueTotalTime);
        dest.writeValue(this.mPrecision);
        dest.writeString(this.mVenueLngLat);
        dest.writeString(this.mAddress);
        dest.writeString(this.mCity);
        dest.writeString(this.mState);
        dest.writeString(this.mCountry);
    }

    public UserData() {
    }

    protected UserData(Parcel in) {
        this.mId = in.readString();
        this.mEuid = in.readString();
        this.mDeviceId = in.readString();
        this.mOs = in.readString();
        this.mOsVersion = in.readString();
        this.mDeviceModel = in.readString();
        this.mHome = in.readString();
        this.mWork = in.readString();
        this.mIstInstalledApps = in.readString();
        this.mBatteryState = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mBatteryPercentage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mArrival = in.readString();
        this.mDeparture = in.readString();
        this.mCategorie = in.readString();
        this.mVenueName = in.readString();
        this.mVenueTotalTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPrecision = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mVenueLngLat = in.readString();
        this.mAddress = in.readString();
        this.mCity = in.readString();
        this.mState = in.readString();
        this.mCountry = in.readString();
    }

    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

}
