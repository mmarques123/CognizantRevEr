package com.example.cognizantrever

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cognizantrever.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

   lateinit var binding:ActivityMainBinding
   var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val constraintLayout = binding.root
        setContentView(constraintLayout)

    }

    override fun onStart() {
        super.onStart()
        var serviceIntent = Intent(this,MyService::class.java)

        binding.btnStart.setOnClickListener {
            serviceIntent.putExtra("url","imageurl.com")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener { stopService(serviceIntent) }

        binding.btnBind.setOnClickListener {
            bindService(serviceIntent,mConnection, BIND_AUTO_CREATE)
        }
        binding.btnUnbind.setOnClickListener {
           // stopService(serviceIntent)
            unbindService(mConnection)
        }
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, localBinder: IBinder?) {
            //var myService = MyService()
            val binder = localBinder as MyService.LocalBinder
             var myService =   binder.getMyService()
             var soccerScore = myService.latestScore()
            Log.i(TAG,"score is--"+soccerScore)
            var sum = myService.add(10,20)

            Log.i(TAG,"sum is--"+sum)

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG,"service disconnected --")

        }
    }

        fun clickHandler(view: View) {
       // EditText nameEdittext = findViewById(R.id.etName)
        var nameEditText : EditText = findViewById(R.id.etName)
        var mainTextView : TextView = findViewById(R.id.tvMain)

        var data = nameEditText.text.toString();
        mainTextView.setText(data)

        var hIntention = Intent(this,HomeActivity::class.java)
        hIntention.putExtra("mykey",data)
        startActivity(hIntention)

    }

    fun openDialer(view: View) {
        var dialerIntention = Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"))
        startActivity(dialerIntention)
    }

    fun setAlarm(view: View) {
        createAlarm("cognizantrev",20,43)
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        //if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        //}
    }

    fun openMyCalendar(view: View) {
        var calIntent = Intent("ineed.water")
        startActivity(calIntent)
    }

    fun sendFlightBroadcast(view: View) {
        var flightIntent = Intent("ihave.flight")
        sendBroadcast(flightIntent)
    }
}