package com.gxd.demo.ntv

import android.app.Activity
import android.os.Bundle
import com.gxd.demo.ntv.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'hello' native library, which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'hello' library on application startup.
        init {
            System.loadLibrary("hello")
        }
    }
}