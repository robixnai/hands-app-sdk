package br.com.rmoreira.sdk.network.api;

import br.com.rmoreira.sdk.pojo.AddressComponent;
import br.com.rmoreira.sdk.pojo.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public interface ApiAddress {

    @GET("json")
    Call<Results<AddressComponent>> getAddress(@Query("latlng") String latLong);

}
