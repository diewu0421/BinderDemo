package com.example.server

import android.app.Service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import java.lang.invoke.MethodHandleInfo

class MainActivity : AppCompatActivity() {
    private val myIntent by lazy {Intent(this, MyService::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(myIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(myIntent)
    }
}

class MyService : Service() {

    val list = mutableListOf<Person>()

    override fun onBind(intent: Intent?): IBinder {
        return mybinder
    }

    private val mybinder = object : IMyAidlInterface.Stub() {
        override fun addPerson(person: Person) {
            Log.e("MyService","addPerson $person")
            list.add(person)
        }

        override fun getPersonList(): MutableList<Person> {
            Log.e("MyService","getPersonList ")
            return list
        }

    }

    override fun onCreate() {
        super.onCreate()
        Log.e("MyService","onCreate ")
    }

}