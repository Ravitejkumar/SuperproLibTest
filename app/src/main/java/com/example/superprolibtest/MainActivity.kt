package com.example.superprolibtest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.videocalllib.ui.loading.LoadingActivity
import com.androidapp.videocalllib.utils.AppConstants.IntentConstants
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val editText1 = findViewById<EditText>(R.id.edittext1)
        val editText2 = findViewById<EditText>(R.id.edittext2)

        //test values//
//        editText1.setText("022-2355-348")
//        editText2.setText("f25bb5da-4d4f-4c37-a62d-416702228abf")


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val meetingId = editText1.text.toString()
            val token = editText2.text.toString()
            if(meetingId.isNotEmpty() && token.isNotEmpty()){
                startSuperproCall(meetingId,token)
            }
        }
    }

    fun startSuperproCall(meetingId: String, token: String) {
        val intent = Intent(this, LoadingActivity::class.java)
        intent.putExtra(IntentConstants.MEETING_ID, meetingId)
        intent.putExtra(IntentConstants.TOKEN, token)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}