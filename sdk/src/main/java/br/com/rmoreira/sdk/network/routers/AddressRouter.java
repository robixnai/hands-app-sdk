package br.com.rmoreira.sdk.network.routers;

import java.io.IOException;
import java.util.List;

import br.com.rmoreira.sdk.network.api.ApiAddress;
import br.com.rmoreira.sdk.network.api.Service;
import br.com.rmoreira.sdk.pojo.AddressComponent;
import br.com.rmoreira.sdk.pojo.Results;
import retrofit2.Call;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class AddressRouter {

    private static final AddressRouter mInstance = new AddressRouter();
    private static final ApiAddress mApi = Service.getServiceAddress();

    private AddressRouter() {}

    public static synchronized AddressRouter getInstance() {
        return mInstance;
    }

    public List<AddressComponent> getAddress(String latLong) throws IOException {
        Call<Results<AddressComponent>> addressComponents = mApi.getAddress(latLong);
        Results<AddressComponent> addressComponentsResults = addressComponents.execute().body();
        if (addressComponentsResults != null) {
            return addressComponentsResults.getData();
        }
        return null;
    }

}
