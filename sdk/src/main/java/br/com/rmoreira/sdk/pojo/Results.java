package br.com.rmoreira.sdk.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class Results<T> {

    @SerializedName("results")
    private List<T> data;

    public List<T> getData() {
        return data;
    }

}
