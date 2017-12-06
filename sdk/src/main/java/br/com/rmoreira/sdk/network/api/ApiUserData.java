package br.com.rmoreira.sdk.network.api;

import java.util.List;

import br.com.rmoreira.sdk.pojo.UserData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public interface ApiUserData {

    @GET("user/data/count")
    Call<Integer> getCount();

    @GET("user/data/all")
    Call<List<UserData>> getAll();

    @GET("user/data/page/{page}")
    Call<List<UserData>> getPage(@Path("page") int page);

    @GET("user/data/{id}")
    Call<UserData> getById(@Path("id") int id);

    @PUT("user/data/{id}")
    Call<UserData> updateUserData(@Path("id") String id, @Body UserData userData);

}
