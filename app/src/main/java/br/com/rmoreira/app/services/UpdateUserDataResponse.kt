package br.com.rmoreira.app.services

import br.com.rmoreira.app.contracts.UserDataDetailInterface
import br.com.rmoreira.sdk.network.RequestCallback
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 05/12/17.
 */
class UpdateUserDataResponse(private var mModel: UserDataDetailInterface.Model) : RequestCallback<Void, UserData, String>() {

    override fun onSuccess(userData: UserData) {
        mModel.response(userData)
    }

    override fun onFailure(error: String) {
        mModel.response(error)
    }

}