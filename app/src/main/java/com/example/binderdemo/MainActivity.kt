package com.example.binderdemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.server.IMyAidlInterface
import com.example.server.Person

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener {

            iMyAidlInterface?.addPerson(Person("zenglw1"))

        }

        findViewById<Button>(R.id.get_button).setOnClickListener {
            Log.e("MainActivity","onCreate ${iMyAidlInterface?.personList}")
        }
        bindService(Intent().apply {
            component = ComponentName("com.example.server", "com.example.server.MyService")
        }, connection, Context.BIND_AUTO_CREATE)
    }

    private var iMyAidlInterface: IMyAidlInterface? = null
    private val connection by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName) {
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}