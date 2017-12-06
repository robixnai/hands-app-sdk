package br.com.rmoreira.sdk.network.api;

/**
 * Created by robsonmoreira on 03/12/17.
 */

public class Service {

    public static ApiUserData getServiceUserData() {
        return Client.getClientUserData().create(ApiUserData.class);
    }

    public static ApiAddress getServiceAddress() {
        return Client.getClientAddress().create(ApiAddress.class);
    }

}
