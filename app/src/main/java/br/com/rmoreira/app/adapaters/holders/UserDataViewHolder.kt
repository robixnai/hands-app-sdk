package br.com.rmoreira.app.adapaters.holders

import android.view.View
import br.com.rmoreira.app.R
import br.com.rmoreira.app.contracts.OnItemClickListener
import br.com.rmoreira.sdk.pojo.UserData
import kotlinx.android.synthetic.main.user_data_item.view.*

/**
 * Created by robsonmoreira on 03/12/17.
 */
class UserDataViewHolder(private val mItemView: View, private val mListener: OnItemClickListener) : GenericViewHolder(mItemView, mListener) {

    companion object {
        val TYPE_ANDROID: String get() = "android"
        val TYPE_IOS: String get() = "ios"
    }

    override fun onBindViewHolder(item: Any) {
        super.onBindViewHolder(item)

        val userData: UserData = item as UserData

        mItemView.textViewDeviceModel.setText(userData.deviceModel)
        mItemView.textViewDeviceId.setText(userData.deviceId)
        mItemView.textViewOsVersion.setText(userData.osVersion)
        mItemView.imageViewBatteryPercentage.setImageResource(getImageResource(userData.batteryPercentage))
        mItemView.textViewBatteryPercentage.setText(userData.batteryPercentage.toString() + "%")
        mItemView.imageViewOs.setImageResource(getOsImageResource(userData.os))
        mItemView.textViewOs.setText(getOsName(userData.os))
    }

    fun getImageResource(batteryPercentage: Int): Int {
        if (batteryPercentage <= 20) {
            return R.drawable.ic_battery_charging_20
        } else if (batteryPercentage > 20 && batteryPercentage <= 30) {
            return R.drawable.ic_battery_charging_30
        } else if (batteryPercentage > 30 && batteryPercentage <= 50) {
            return R.drawable.ic_battery_charging_50
        } else if (batteryPercentage > 50 && batteryPercentage <= 60) {
            return R.drawable.ic_battery_charging_60
        } else if (batteryPercentage > 60 && batteryPercentage <= 80) {
            return R.drawable.ic_battery_charging_80
        } else if (batteryPercentage > 80 && batteryPercentage <= 90) {
            return R.drawable.ic_battery_charging_90
        } else {
            return R.drawable.ic_battery_charging_100
        }
    }

    fun getOsImageResource(os: String): Int {
        if (TYPE_ANDROID.equals(os)) {
            return R.drawable.ic_android
        } else {
            return R.drawable.ic_apple
        }
    }

    fun getOsName(os: String): String {
        if (TYPE_ANDROID.equals(os)) {
            return mItemView.context.getString(R.string.lbl_android)
        } else {
            return mItemView.context.getString(R.string.lbl_ios)
        }
    }

}