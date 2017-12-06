package br.com.rmoreira.app.services

import br.com.rmoreira.app.contracts.UserDataDetailInterface
import br.com.rmoreira.sdk.network.RequestCallback
import br.com.rmoreira.sdk.pojo.Address

/**
 * Created by robsonmoreira on 05/12/17.
 */
class GetAddressResponse(private var mModel: UserDataDetailInterface.Model) : RequestCallback<Void, List<Address>, String>() {

    override fun onSuccess(result: List<Address>?) {
        mModel.response(result)
    }

    override fun onFailure(errors: String?) {
        mModel.response(errors)
    }

}