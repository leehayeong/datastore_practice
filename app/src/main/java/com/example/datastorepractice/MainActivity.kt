package com.example.datastorepractice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.datastorepractice.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClicks()
        setObserve()
    }

    private fun setClicks() {
        binding.whiteSetButton.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    SampleApplication.getInstance()
                        .getProtoSettingManager()
                        .updateColor(UserSettings.BgColor.COLOR_WHITE)
                }
            }
        }

        binding.blackSetButton.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    SampleApplication.getInstance()
                        .getProtoSettingManager()
                        .updateColor(UserSettings.BgColor.COLOR_BLACK)
                }
            }
        }
    }

    private fun setObserve() {
        SampleApplication.getInstance()
            .getProtoSettingManager()
            .getColor().asLiveData().observe(this) {
                when (it.bgColor) {
                    UserSettings.BgColor.COLOR_WHITE -> {
                        binding.layout.setBackgroundColor(Color.WHITE)
                    }
                    UserSettings.BgColor.COLOR_BLACK -> {
                        binding.layout.setBackgroundColor(Color.BLACK)
                    }
                    else -> {
                    }
                }
            }
    }
}
