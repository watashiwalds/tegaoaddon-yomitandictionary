package com.tegaoteam.addon.tegao.yomitandictionary

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LookupService : Service() {
    private var trustedUid: Int? = null
    private var trustedPackageName = AddonApplication.parentPackage
    private fun firstVerify(callingUid: Int) {
        val callerPackages = packageManager.getPackagesForUid(callingUid)
        val callerPackageName = callerPackages?.firstOrNull()
        trustedUid = if (callerPackageName == trustedPackageName) callingUid else -1
    }

    private var lookupCallback: ILookupCallback? = null

    private val binder = object: ILookupService.Stub() {
        override fun requestLookupResult(type: Int, keyword: String?) {
            Log.i("RecognitionService", "Received request to suggesting by array ${keyword?.length}")
            val callingUid = getCallingUid()
            if (trustedUid == null) firstVerify(callingUid)
            if (callingUid != trustedUid) lookupCallback?.onResult(null)
            Log.i("RecognitionService", "Request confirmed by trusted package")
        }

        override fun registerCallback(callback: ILookupCallback) {
            Log.i("RecognitionService", "Request to register callback $callback")
            val callingUid = getCallingUid()
            if (trustedUid == null) firstVerify(callingUid)
            if (callingUid != trustedUid) return

            lookupCallback = callback
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("LookupService", "Service created")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("LookupService", "Service bind with correct explicit intent call, caller unidentified until first use")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("LookupService", "Service unbind from $trustedUid which was ${if (trustedUid != -1) "trusted" else "not trusted"}")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LookupService", "Service destroyed")
    }
}