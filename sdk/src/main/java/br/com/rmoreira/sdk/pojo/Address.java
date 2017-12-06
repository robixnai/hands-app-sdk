package br.com.rmoreira.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class Address implements Parcelable {

    @SerializedName("long_name")
    private String mLongName;

    @SerializedName("short_name")
    private String mShortName;

    @SerializedName("types")
    private List<String> mTypes;

    public String getLongName() {
        return mLongName;
    }

    public String getShortName() {
        return mShortName;
    }

    public List<String> getTypes() {
        return mTypes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLongName);
        dest.writeString(this.mShortName);
        dest.writeStringList(this.mTypes);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.mLongName = in.readString();
        this.mShortName = in.readString();
        this.mTypes = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

}
