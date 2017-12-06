package br.com.rmoreira.app.models

import br.com.rmoreira.app.contracts.UserDataDetailInterface
import br.com.rmoreira.app.services.GetAddressResponse
import br.com.rmoreira.app.services.UpdateUserDataResponse
import br.com.rmoreira.sdk.models.AddressModel
import br.com.rmoreira.sdk.models.GpsTrackerModel
import br.com.rmoreira.sdk.models.UserDataModel
import br.com.rmoreira.sdk.pojo.Address
import br.com.rmoreira.sdk.pojo.UserData
import br.com.rmoreira.app.objects.Address as AddressObject

/**
 * Created by robsonmoreira on 05/12/17.
 */
class UserDataDetailModel(private var mPresenter: UserDataDetailInterface.Presenter) : UserDataDetailInterface.Model {

    private var mLatitude: Double = 0.0
    private var mLongitude: Double = 0.0

    private var getAddressResponse: GetAddressResponse
    private var updateUserDataResponse: UpdateUserDataResponse

    init {
        getAddressResponse = GetAddressResponse(this@UserDataDetailModel)
        updateUserDataResponse = UpdateUserDataResponse(this@UserDataDetailModel)
    }

    override fun getLocation() {
        val gpsTrackerModel = GpsTrackerModel()
        if (gpsTrackerModel.canGetLocation()) {
            mLatitude = gpsTrackerModel.getLatitude()
            mLongitude = gpsTrackerModel.getLongitude()

            val latLong = mLatitude.toString() + ", " + mLongitude.toString()
            AddressModel(getAddressResponse).getAddress(latLong)
        } else {
            mPresenter.isGpsEnabled(false)
        }
    }

    override fun getLatitide(): Double {
        return mLatitude
    }

    override fun getLongitude(): Double {
        return mLongitude
    }

    override fun response(response: Any?) {
        if (response is String || response is UserData) {
            mPresenter.response(response)
        } else {
            mPresenter.response(getAddress(response as MutableList<Address>))
        }
    }

    override fun updateUserData(userData: UserData) {
        UserDataModel(updateUserDataResponse).updateUserData(userData)
    }

    private fun getAddress(addressList: MutableList<Address>): AddressObject {
        val addressObejct = AddressObject
        addressObejct.address = addressList.get(1).shortName
        addressObejct.city = addressList.get(3).shortName
        addressObejct.state = addressList.get(5).shortName
        addressObejct.country = addressList.get(6).shortName
        return addressObejct
    }

}