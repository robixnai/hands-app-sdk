package br.com.rmoreira.sdk.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class AddressComponent {

    @SerializedName("address_components")
    private List<Address> mAddressList;

    @SerializedName("types")
    private List<String> mTypes;

    public List<Address> getAddressList() {
        return mAddressList;
    }

    public List<String> getTypes() {
        return mTypes;
    }

}
