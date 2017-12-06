package br.com.rmoreira.app.presenters

import android.view.View
import br.com.rmoreira.app.contracts.UserDataInterface
import br.com.rmoreira.app.models.UserDataModel
import br.com.rmoreira.sdk.helpers.SdkHelpers
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 04/12/17.
 */
class UserDataPresenter(private var mView: UserDataInterface.View) : UserDataInterface.Presenter {

    private var mPage: Int? = null
    private var mModel: UserDataInterface.Model

    init {
        mModel = UserDataModel(this@UserDataPresenter)
        mView.checkAndRequestPermissions()
    }

    override fun getUserData(page: Int) {
        if (SdkHelpers.isNetworkAvailable(mView.getContext())) {
            if (page == 1)
                mView.showProgressBar(View.VISIBLE)

            mPage = page
            mModel.getUserData(page)
        } else {
            mView.showConnectionError()
        }
    }

    override fun response(response: Any?) {
        if (response is String) {
            mView.showRequestError(response)
        } else {
            mView.updateUserDataList(response as MutableList<UserData>, mPage!! > 1)
        }
        mView.showProgressBar(View.GONE)
    }

}