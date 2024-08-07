package com.example.cognizantrever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log


class SmsReceiver : BroadcastReceiver() {
//android - 6 runtime permission

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "abdul u 've sms -- cognizant")
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus!!.size)
                for (i in pdus!!.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                }
                if (messages.size > -1) {
                    Log.i(TAG, "Message recieved: " + messages[0]?.getMessageBody())
                    Log.i(TAG, "phno is : " + messages[0]?.displayOriginatingAddress)

                }
            }
        }



    companion object{
        var TAG = SmsReceiver::class.java.simpleName
    }
}