package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.articleImage.load(R.drawable.article2_domenicali)

        binding.readSwitch.isChecked = savedInstanceState?.getBoolean(STATE_READ, false)
            ?: intent.getBooleanExtra(EXTRA_READ, false)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            Log.d(TAG, "read switch -> $isChecked")
        }

        binding.toolbar.setNavigationOnClickListener {
            Log.d(TAG, "back pressed via toolbar")
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            Log.d(TAG, "back pressed (dispatcher)")
            finishWithResult(binding.readSwitch.isChecked)
        }
    }

    private fun finishWithResult(isRead: Boolean) {
        setResult(
            RESULT_OK,
            Intent().putExtra(EXTRA_READ, isRead)
        )
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_READ, binding.readSwitch.isChecked)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        private const val TAG = "SecondActivity"
        const val EXTRA_READ = "extra_read"
        private const val STATE_READ = "state_read"
    }
}
