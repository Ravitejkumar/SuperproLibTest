package com.example.superprolibtest

//import com.androidapp.videocalllib.utils.AppConstants.IntentConstants
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.androidapp.videocalllib.ui.loading.SuperProActivity
import com.androidapp.videocalllib.ui.loading.SuperProCallListener
import com.example.superprolibtest.databinding.ActivityMainBinding

class MainActivity : SuperProActivity(), SuperProCallListener {

    private lateinit var binding: ActivityMainBinding
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.btnJoinCall.setOnClickListener { view ->
            val meetingId = binding.edittext1.text.toString()
            val token = binding.edittext2.text.toString()

            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            if(meetingId.isNotEmpty() && token.isNotEmpty()){
                setCallFrame(binding.callFrame)
                startCall(meetingId, token, this@MainActivity)
            } else {
                Toast.makeText(this@MainActivity,"MeetingId or token is invalid.",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun audioStatusChange(isEnabled: Boolean) {
        val text = if(isEnabled) "Audio Enabled" else "Audio Disabled"
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    }

    override fun callStatusChange(status: String) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
    }

    override fun videoStatusChange(isEnabled: Boolean) {
        val text = if(isEnabled) "Video Enabled" else "Video Disabled"
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}