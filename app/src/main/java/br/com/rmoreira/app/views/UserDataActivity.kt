package br.com.rmoreira.app.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import br.com.rmoreira.app.R
import br.com.rmoreira.app.presenters.UserDataPresenter
import br.com.rmoreira.app.adapaters.EndlessRecyclerOnScrollListener
import br.com.rmoreira.app.adapaters.GenericAdapter
import br.com.rmoreira.app.contracts.OnItemClickListener
import br.com.rmoreira.app.contracts.UserDataInterface
import br.com.rmoreira.sdk.pojo.UserData
import kotlinx.android.synthetic.main.activity_user_data.*
import org.jetbrains.anko.alert
import java.util.ArrayList
import java.util.HashMap

class UserDataActivity : AppCompatActivity(), UserDataInterface.View, OnItemClickListener {

    companion object {
        val REQUEST_PERMISSION: Int get() = 123
        val EXTRA_USER_DATA: String get() = "user_data"
    }

    private lateinit var mAdapter: GenericAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mPresenter: UserDataInterface.Presenter

    private var mOnScrollListener: RecyclerView.OnScrollListener? = null
    private var mUserData: MutableList<Any> = ArrayList()
    private var mPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        mPresenter = UserDataPresenter(this@UserDataActivity)
    }

    override fun onStart() {
        super.onStart()

        mAdapter = GenericAdapter(mUserData, this@UserDataActivity)
        mLayoutManager = LinearLayoutManager(this@UserDataActivity)

        refreshScrollListener(recyclerViewUserData)
    }

    override fun onResume() {
        super.onResume()

        bindAdapter()
        mPresenter.getUserData(mPage)
    }

    override fun getContext(): Context {
        return this@UserDataActivity
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION -> {
                val perms = HashMap<String, Int>()
                perms.put(Manifest.permission.ACCESS_NETWORK_STATE, PackageManager.PERMISSION_GRANTED)
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED)
                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED)

                if (grantResults.size > 0) {
                    for (i in permissions.indices)
                        perms.put(permissions[i], grantResults[i])
                }
            }
        }
    }

    override fun checkAndRequestPermissions() {
        val networkStatePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
        val fineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        val listPermissionsNeeded = ArrayList<String>()
        if (networkStatePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE)
        }
        if (fineLocationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (coarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), REQUEST_PERMISSION)
        }
    }

    override fun updateUserDataList(userDataList: MutableList<UserData>, loading: Boolean) {
        if (mUserData.size > 0)
            mUserData.removeAt(mUserData.size - 1)

        mUserData.addAll(userDataList)
        mAdapter.updateItens(mUserData, loading)
    }

    override fun showProgressBar(visibility: Int) {
        includeProgressBar.visibility = visibility
    }

    override fun showNoData(visibility: Int) {
        includeNoData.visibility = visibility
    }

    override fun showConnectionError() {
        Toast.makeText(this@UserDataActivity, getString(R.string.msg_connection_error), Toast.LENGTH_LONG).show()
    }

    override fun showRequestError(message: String) {
        alert(message) {
            title(getString(R.string.msg_title_alert))
            cancellable(false)
            positiveButton(getString(R.string.msg_action_ok)) { }
        }.show()
    }

    override fun onItemClick(v: View, o: Any) {
        val userData = o as UserData
        val intent = Intent(this@UserDataActivity, UserDataDetailActivity::class.java)
        intent.putExtra(EXTRA_USER_DATA, userData)
        startActivity(intent)
    }

    private fun bindAdapter() {
        with(recyclerViewUserData) {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    private fun refreshScrollListener(recyclerView: RecyclerView) {
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener)
        }

        mOnScrollListener = object : EndlessRecyclerOnScrollListener(mLayoutManager) {

            override fun onLoadMore(currentPage: Int) {
                mPage = currentPage
                mPresenter.getUserData(mPage)
            }

        }
        recyclerView.addOnScrollListener(mOnScrollListener)
    }

}
