package br.com.rmoreira.app.presenters

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.rmoreira.app.R
import br.com.rmoreira.app.contracts.UserDataDetailInterface
import br.com.rmoreira.app.models.UserDataDetailModel
import br.com.rmoreira.app.objects.Address
import br.com.rmoreira.app.views.MapsActivity
import br.com.rmoreira.app.views.UserDataActivity
import br.com.rmoreira.app.views.UserDataDetailActivity
import br.com.rmoreira.sdk.helpers.SdkHelpers
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 05/12/17.
 */
class UserDataDetailPresenter(private var mView: UserDataDetailInterface.View) : UserDataDetailInterface.Presenter {

    companion object {
        val TYPE_HOME: String get() = "home"
        val TYPE_WORK: String get() = "work"
        val TYPE_LNG_LAT: String get() = "venueLngLat"
        val TYPE_ANDROID: String get() = "android"
        val TYPE_IOS: String get() = "ios"
    }

    private var mUserData: UserData? = null
    private var mKeyboardArrowDow: Boolean = true
    private var mModel: UserDataDetailInterface.Model

    init {
        mModel = UserDataDetailModel(this@UserDataDetailPresenter)
    }

    override fun setUserData(extras: Bundle?) {
        if (extras != null) {
            mUserData = extras.getParcelable<UserData>(UserDataActivity.EXTRA_USER_DATA)
        }
    }

    override fun getTitleActionBar(): String {
        if (TYPE_ANDROID.equals(mUserData!!.os)) {
            return mView.getContext().getString(R.string.lbl_android_title_detail, mUserData!!.osVersion)
        } else {
            return mView.getContext().getString(R.string.lbl_ios_title_detail, mUserData!!.osVersion)
        }
    }

    override fun getResourceToBackdrop(): Int {
        if (TYPE_ANDROID.equals(mUserData!!.os)) {
            return R.drawable.android
        } else {
            return R.drawable.apple
        }
    }

    override fun bindElements() {
        val activity = mView.getContext() as UserDataDetailActivity

        activity.findViewById<TextView>(R.id.deviceId).text = mUserData!!.deviceId
        activity.findViewById<TextView>(R.id.deviceModel).text = mUserData!!.deviceModel
        activity.findViewById<TextView>(R.id.batteryState).text = mUserData!!.batteryState.toString()
        activity.findViewById<TextView>(R.id.batteryPercentage).text = mUserData!!.batteryPercentage.toString() + "%"
        activity.findViewById<TextView>(R.id.departure).text = mUserData!!.departure
        activity.findViewById<TextView>(R.id.arrival).text = mUserData!!.arrival
        activity.findViewById<TextView>(R.id.categorie).text = mUserData!!.categorie
        activity.findViewById<TextView>(R.id.venueName).text = mUserData!!.venueName
        activity.findViewById<TextView>(R.id.venueTotalTime).text = mUserData!!.venueTotalTime.toString()
        activity.findViewById<TextView>(R.id.precision).text = mUserData!!.precision.toString()
        activity.findViewById<TextView>(R.id.address).text = mUserData!!.address
        activity.findViewById<TextView>(R.id.city).text = mUserData!!.city
        activity.findViewById<TextView>(R.id.state).text = mUserData!!.state
        activity.findViewById<TextView>(R.id.country).text = mUserData!!.country

        if ("null".equals(mUserData!!.istInstalledApps)) {
            activity.findViewById<CardView>(R.id.cardViewIstInstalledApps).visibility = View.GONE
        } else {
            val installedApps = mUserData!!.istInstalledApps.split("|")
            activity.findViewById<TextView>(R.id.countAppInstaled).text = installedApps.size.toString() + " " + mView.getContext().getString(R.string.lbl_apps_instaled)

            for (apps in installedApps) {
                val textView = TextView(mView.getContext())
                textView.text = "- " + apps
                activity.findViewById<LinearLayout>(R.id.istInstalledApps).addView(textView)
            }
        }
    }

    override fun clickViewIstInstalledApps() {
        val activity = mView.getContext() as UserDataDetailActivity
        if (mKeyboardArrowDow) {
            activity.findViewById<LinearLayout>(R.id.linearLayourSizeApps).visibility = View.GONE
            activity.findViewById<LinearLayout>(R.id.istInstalledApps).visibility = View.VISIBLE
            activity.findViewById<ImageView>(R.id.actionCardApps).setImageResource(R.drawable.ic_keyboard_arrow_up_24dp)
        } else {
            activity.findViewById<LinearLayout>(R.id.linearLayourSizeApps).visibility = View.VISIBLE
            activity.findViewById<LinearLayout>(R.id.istInstalledApps).visibility = View.GONE
            activity.findViewById<ImageView>(R.id.actionCardApps).setImageResource(R.drawable.ic_keyboard_arrow_down_24dp)
        }
        mKeyboardArrowDow = !mKeyboardArrowDow
    }

    override fun openMaps(origin: String) {
        val intent = Intent(mView.getContext(), MapsActivity::class.java)
        if (TYPE_HOME.equals(origin)) {
            intent.putExtra(UserDataDetailActivity.EXTRA_LNG_LAT, mUserData!!.home)
            intent.putExtra(UserDataDetailActivity.EXTRA_ADDRESS, mView.getContext().getString(R.string.lbl_home))
        } else if (TYPE_WORK.equals(origin)) {
            intent.putExtra(UserDataDetailActivity.EXTRA_LNG_LAT, mUserData!!.work)
            intent.putExtra(UserDataDetailActivity.EXTRA_ADDRESS, mView.getContext().getString(R.string.lbl_work))
        } else {
            intent.putExtra(UserDataDetailActivity.EXTRA_LNG_LAT, mUserData!!.venueLngLat)
            intent.putExtra(UserDataDetailActivity.EXTRA_ADDRESS, mUserData!!.address)
        }
        mView.getContext().startActivity(intent)
    }

    override fun openSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        mView.getContext().startActivity(intent);
    }

    override fun getLocation() {
        if (SdkHelpers.isNetworkAvailable(mView.getContext())) {
            mView.showProgressDialog()
            mModel.getLocation()
        } else {
            mView.showConnectionError()
        }
    }

    override fun isGpsEnabled(status: Boolean) {
        if (!status) {
            mView.dismissProgressDialog()
            mView.showSettingsAlert()
        }
    }

    override fun response(response: Any?) {
        if (response is String) {
            mView.dismissProgressDialog()
            mView.showRequestError(response)
        } else if (response is Address) {
            val longLat = mModel.getLongitude().toString() + " " + mModel.getLatitide().toString()

            mUserData!!.venueLngLat = longLat
            mUserData!!.address = response.address
            mUserData!!.city = response.city
            mUserData!!.state = response.state
            mUserData!!.country = response.country

            mModel.updateUserData(mUserData!!)
        } else {
            val activity = mView.getContext() as UserDataDetailActivity
            activity.findViewById<TextView>(R.id.address).text = mUserData!!.address
            activity.findViewById<TextView>(R.id.city).text = mUserData!!.city
            activity.findViewById<TextView>(R.id.state).text = mUserData!!.state
            activity.findViewById<TextView>(R.id.country).text = mUserData!!.country

            mView.showSuccessAlert()
            mView.dismissProgressDialog()
        }
    }

}