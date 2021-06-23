package com.example.superprolibtest;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.androidapp.videocalllib.SuperproKt;
import com.androidapp.videocalllib.ui.loading.SuperproFragment;
import com.androidapp.videocalllib.utils.AppConstants;
import com.example.superprolibtest.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity implements SuperproFragment.SuperProCallListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJoinCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meetingId = binding.edittext1.getText().toString();
                String token = binding.edittext2.getText().toString();
                meetingId = "b11-4667-a7a";
                token = "206f30f4-b6c2-4a37-ba84-c5ba7f60cdaa";
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.IntentConstants.SUPERPRO_MEETING_ID, meetingId);
                bundle.putString(AppConstants.IntentConstants.SUPERPRO_TOKEN,token);

                FragmentManager fm = getSupportFragmentManager();
                SuperproFragment fragment = SuperproKt.startVideoCall(meetingId,token);
                fm.beginTransaction().replace(binding.callFrame.getId(),fragment).commit();
                fragment.setListener(MainActivity2.this);
            }
        });
    }

    @Override
    public void audioStatusChange(boolean b) {

    }

    @Override
    public void callStatusChange(@NotNull String s) {

    }

    @Override
    public void videoStatusChange(boolean b) {

    }
}
