package com.siderfighter.entertainmenttracker.applayers.presentation.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.siderfighter.entertainmenttracker.applayers.utils.LOG_TAG

private const val SMS_RETRIEVED_ACTION = "com.google.android.gms.auth.api.phone.SMS_RETRIEVED"
private const val SMS_RETRIEVE_STATUS = "com.google.android.gms.auth.api.phone.EXTRA_STATUS"
private const val SMS_RETRIEVED_MESSAGE = "com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE"

class SmsBroadcastReceiver : BroadcastReceiver() {

    private var onSmsReceived: (sms: String) -> Unit = {}
    private var onSmsTimeOut: () -> Unit = {}

    fun setSmsListener(onSmsReceived: (sms: String) -> Unit, onSmsTimeOut: () -> Unit = {}) {
        this.onSmsReceived = onSmsReceived
        this.onSmsTimeOut = onSmsTimeOut
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == SMS_RETRIEVED_ACTION) {
            val extras = intent.extras
            val status = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras?.getParcelable(SMS_RETRIEVE_STATUS, Status::class.java)
            } else {
                extras?.getParcelable(SMS_RETRIEVE_STATUS)
            }
            when (status?.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras?.getString(SMS_RETRIEVED_MESSAGE)
                    Log.d(LOG_TAG, "SMS Received, sms is $message")
                    message?.let {
                        onSmsReceived(it)
                    }
                }

                CommonStatusCodes.TIMEOUT -> {
                    Log.d(LOG_TAG, "SMS Timeout")
                    onSmsTimeOut()
                }
            }
        }
    }
}