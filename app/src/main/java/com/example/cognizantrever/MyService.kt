package com.example.cognizantrever

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * serving the latest cricket score
 * add 2 nos
 */
class MyService : Service() {
    var TAG = MyService::class.java.simpleName
    private val localBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getMyService(): MyService = this@MyService

    }

        override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"my service created")
    }

    fun latestScore():Int{
        return 5;
    }

    fun add(a:Int, b:Int):Int{
        return a+b
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Log.i(TAG,"my service started--"+intent?.getStringExtra("url"))
        var player = MediaPlayer.create(this,R.raw.tune)
        player.start()
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"my service destroyed")

    }

    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG,"some activity is trying to bind to this service")
        return localBinder

    }
}