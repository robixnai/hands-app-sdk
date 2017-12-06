package br.com.rmoreira.app.contracts

import android.content.Context
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 04/12/17.
 */
interface UserDataInterface {

    interface Model {
        fun getUserData(page: Int)
        fun response(response: Any?)
    }

    interface View {
        fun getContext(): Context
        fun checkAndRequestPermissions()
        fun updateUserDataList(userDataList: MutableList<UserData>, loading: Boolean)
        fun showProgressBar(visibility: Int)
        fun showConnectionError()
        fun showRequestError(message: String)
    }

    interface Presenter {
        fun getUserData(page: Int)
        fun response(response: Any?)
    }

}