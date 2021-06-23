package com.example.superprolibtest

//import com.androidapp.videocalllib.utils.AppConstants.IntentConstants
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.androidapp.videocalllib.startVideoCall
import com.androidapp.videocalllib.ui.joiningSetup.HeadsetReceiver
import com.androidapp.videocalllib.ui.loading.SuperproFragment.SuperProCallListener
import com.androidapp.videocalllib.utils.AppConstants
import com.example.superprolibtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,SuperProCallListener {

    private lateinit var binding: ActivityMainBinding
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnJoinCall.setOnClickListener { view ->
            var meetingId = binding.edittext1.text.toString()
            var token = binding.edittext2.text.toString()

            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
             meetingId = "b11-4667-a7a";
             token = "206f30f4-b6c2-4a37-ba84-c5ba7f60cdaa";
            if(meetingId.isNotEmpty() && token.isNotEmpty()){
//
                val bundle = Bundle()
                bundle.putString(AppConstants.IntentConstants.SUPERPRO_MEETING_ID, meetingId)
                bundle.putString(AppConstants.IntentConstants.SUPERPRO_TOKEN, token)

                val fragment = startVideoCall(meetingId, token)
                supportFragmentManager.beginTransaction().replace(binding.callFrame.id, fragment).commit()
                fragment.setListener(this)
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