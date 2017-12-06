package br.com.rmoreira.app.services

import br.com.rmoreira.app.contracts.UserDataInterface
import br.com.rmoreira.sdk.network.RequestCallback
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 04/12/17.
 */
class GetUserDataPerPageResponse(private var mModel: UserDataInterface.Model) : RequestCallback<Void, MutableList<UserData>, String>() {

    override fun onSuccess(userDataList: MutableList<UserData>) {
        mModel.response(userDataList)
    }

    override fun onFailure(error: String) {
        mModel.response(error)
    }

}