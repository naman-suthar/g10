package com.vrcareer.g10assignment

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.vrcareer.g10assignment.api.ApiHelper
import com.vrcareer.g10assignment.api.MyApi
import com.vrcareer.g10assignment.api.RetrofitClient.client
import com.vrcareer.g10assignment.model.ApiResult

sealed class Status {
    data class SUCCESS(val data: ApiResult): Status()
    data class FAILURE(val message: String): Status()
    object LOADING: Status()
}

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
    companion object {

        private var mApiHelper: ApiHelper? = null
        fun getApiHelperInstance():ApiHelper{
            if(mApiHelper==null){
                mApiHelper = ApiHelper(client!!.create(MyApi::class.java))
            }
            return mApiHelper!!
        }
    }
}
