package br.com.rmoreira.app.views

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.rmoreira.app.R
import kotlinx.android.synthetic.main.activity_user_data_detail.*
import kotlinx.android.synthetic.main.content_user_data_details.*
import br.com.rmoreira.app.contracts.UserDataDetailInterface
import br.com.rmoreira.app.presenters.UserDataDetailPresenter
import org.jetbrains.anko.alert

class UserDataDetailActivity : AppCompatActivity(), UserDataDetailInterface.View, View.OnClickListener {

    companion object {
        val EXTRA_LNG_LAT: String get() = "venue_lng_lat"
        val EXTRA_ADDRESS: String get() = "address"
    }

    private lateinit var mPresenter: UserDataDetailInterface.Presenter
    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data_detail)

        mPresenter = UserDataDetailPresenter(this@UserDataDetailActivity)
        mPresenter.setUserData(intent.extras)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(mPresenter.getTitleActionBar())
        backdrop.setImageResource(mPresenter.getResourceToBackdrop())
    }

    override fun onStart() {
        super.onStart()
        mPresenter.bindElements()

        actionCardApps.setOnClickListener(this@UserDataDetailActivity)
        home.setOnClickListener(this@UserDataDetailActivity)
        work.setOnClickListener(this@UserDataDetailActivity)
        cardViewlocation.setOnClickListener(this@UserDataDetailActivity)
        updateLocal.setOnClickListener(this@UserDataDetailActivity)
    }

    override fun getContext(): Context {
        return this@UserDataDetailActivity
    }

    override fun showConnectionError() {
        Toast.makeText(this@UserDataDetailActivity, getString(R.string.msg_connection_error), Toast.LENGTH_LONG).show()
    }

    override fun showRequestError(message: String) {
        alert(message) {
            title(getString(R.string.msg_title_alert))
            cancellable(false)
            positiveButton(getString(R.string.msg_action_ok)) { }
        }.show()
    }

    override fun showSettingsAlert() {
        alert(getString(R.string.msg_gps_enable)) {
            title(getString(R.string.msg_title_alert))
            cancellable(false)
            positiveButton(getString(R.string.msg_action_yes)) { mPresenter.openSettings() }
            negativeButton(getString(R.string.msg_action_no)) { }
        }.show()
    }

    override fun showSuccessAlert() {
        alert(getString(R.string.msg_location_update)) {
            title(getString(R.string.msg_title_hands))
            cancellable(false)
            positiveButton(getString(R.string.msg_action_ok)) {}
        }.show()
    }

    override fun showProgressDialog() {
        mProgressDialog = ProgressDialog(this@UserDataDetailActivity)
        mProgressDialog.setIndeterminate(true)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog.setMessage(getString(R.string.msg_updating_location))
        mProgressDialog.show()
    }

    override fun dismissProgressDialog() {
        mProgressDialog.dismiss()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            actionCardApps.id -> mPresenter.clickViewIstInstalledApps()
            home.id -> mPresenter.openMaps(UserDataDetailPresenter.TYPE_HOME)
            work.id -> mPresenter.openMaps(UserDataDetailPresenter.TYPE_WORK)
            cardViewlocation.id -> mPresenter.openMaps(UserDataDetailPresenter.TYPE_LNG_LAT)
            updateLocal.id -> mPresenter.getLocation()
        }
    }

}
