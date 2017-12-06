package br.com.rmoreira.app

import android.app.Application
import android.content.Context
import br.com.rmoreira.sdk.SdkApplication

/**
 * Created by robsonmoreira on 03/12/17.
 */
class AppHands : Application() {

    private lateinit var mInstance: AppHands

    override fun onCreate() {
        super.onCreate()

        mInstance = this

        SdkApplication.setApplication(this);
    }

    companion object {
        private lateinit var mInstance: AppHands

        fun getContext(): Context {
            return mInstance.applicationContext
        }
    }

}