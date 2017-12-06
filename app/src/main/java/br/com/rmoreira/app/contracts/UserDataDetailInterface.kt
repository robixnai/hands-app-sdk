package br.com.rmoreira.app.contracts

import android.content.Context
import android.os.Bundle
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 05/12/17.
 */
interface UserDataDetailInterface {

    interface Model {
        fun getLocation()
        fun getLatitide(): Double
        fun getLongitude(): Double
        fun response(response: Any?)
        fun updateUserData(userData: UserData)
    }

    interface View {
        fun getContext(): Context
        fun showConnectionError()
        fun showRequestError(message: String)
        fun showSettingsAlert()
        fun showSuccessAlert()
        fun showProgressDialog()
        fun dismissProgressDialog()
    }

    interface Presenter {
        fun setUserData(extras: Bundle?)
        fun getTitleActionBar(): String
        fun getResourceToBackdrop(): Int
        fun bindElements()
        fun clickViewIstInstalledApps()
        fun openMaps(origin: String)
        fun openSettings()
        fun getLocation()
        fun isGpsEnabled(status: Boolean)
        fun response(response: Any?)
    }

}