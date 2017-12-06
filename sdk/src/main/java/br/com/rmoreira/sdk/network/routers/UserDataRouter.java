package br.com.rmoreira.sdk.network.routers;

import java.io.IOException;
import java.util.List;

import br.com.rmoreira.sdk.network.api.ApiUserData;
import br.com.rmoreira.sdk.network.api.Service;
import br.com.rmoreira.sdk.pojo.UserData;
import retrofit2.Call;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public class UserDataRouter {

    private static final UserDataRouter mInstance = new UserDataRouter();
    private static final ApiUserData mApi = Service.getServiceUserData();

    private UserDataRouter() {}

    public static synchronized UserDataRouter getInstance() {
        return mInstance;
    }

    public Integer getCount() throws IOException {
        Call<Integer> size = mApi.getCount();
        return size.execute().body();
    }

    public List<UserData> getAll() throws IOException {
        Call<List<UserData>> userDataList = mApi.getAll();
        return userDataList.execute().body();
    }

    public List<UserData> getPage(int page) throws IOException {
        Call<List<UserData>> userDataList = mApi.getPage(page);
        return userDataList.execute().body();
    }

    public UserData getById(int id) throws IOException {
        Call<UserData> userData = mApi.getById(id);
        return userData.execute().body();
    }

    public UserData updateUserData(UserData userData) throws IOException {
        Call<UserData> userDataResponse = mApi.updateUserData(userData.getId(), userData);
        return userDataResponse.execute().body();
    }

}
