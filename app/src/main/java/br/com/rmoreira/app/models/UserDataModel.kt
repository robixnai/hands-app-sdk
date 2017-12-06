package br.com.rmoreira.app.models

import br.com.rmoreira.app.contracts.UserDataInterface
import br.com.rmoreira.app.services.GetUserDataPerPageResponse
import br.com.rmoreira.sdk.models.UserDataModel

/**
 * Created by robsonmoreira on 04/12/17.
 */
class UserDataModel(private var mPresenter: UserDataInterface.Presenter) : UserDataInterface.Model {

    private var mGetUserDataPerPageResponse: GetUserDataPerPageResponse

    init {
        mGetUserDataPerPageResponse = GetUserDataPerPageResponse(this@UserDataModel)
    }

    override fun getUserData(page: Int) {
        UserDataModel(mGetUserDataPerPageResponse).getPage(page)
    }

    override fun response(response: Any?) {
        mPresenter.response(response)
    }

}