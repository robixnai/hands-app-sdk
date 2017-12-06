package br.com.rmoreira.sdk.models;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.rmoreira.sdk.R;
import br.com.rmoreira.sdk.SdkApplication;
import br.com.rmoreira.sdk.network.RequestCallback;
import br.com.rmoreira.sdk.network.routers.AddressRouter;
import br.com.rmoreira.sdk.pojo.AddressComponent;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class AddressModel {

    private final AddressRouter mRouter = AddressRouter.getInstance();

    private RequestCallback mRequestCallback;

    public AddressModel(RequestCallback requestCallback) {
        mRequestCallback = requestCallback;
    }

    @SuppressLint("StaticFieldLeak")
    public void getAddress(String latLong) {
        new AsyncTask<String, Void, List<AddressComponent>>() {

            @Override
            protected void onPreExecute() {
                mRequestCallback.onPreExecute();
            }

            @Override
            protected List<AddressComponent> doInBackground(String... latLong) {
                List<AddressComponent> addressComponents = null;
                try {
                    addressComponents = mRouter.getAddress(latLong[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return addressComponents;
            }

            @Override
            protected void onPostExecute(List<AddressComponent> addressComponents) {
                if (addressComponents != null && addressComponents.size() > 0) {
                    AddressComponent addressComponent = addressComponents.get(0);
                    mRequestCallback.onSuccess(addressComponent.getAddressList());
                } else {
                    mRequestCallback.onFailure(SdkApplication.getContext().getString(R.string.msg_network_put_error));
                }
            }

        }.execute(latLong);
    }

}
