package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var rating by mutableStateOf(0)
    private var secondRead by mutableStateOf(false)

    private val openSecondArticle =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data?.hasExtra(SecondActivity.EXTRA_READ) == true) {
                secondRead = data.getBooleanExtra(SecondActivity.EXTRA_READ, false)
                Log.d(TAG, "onActivityResult: second article read = $secondRead")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        rating = savedInstanceState?.getInt(STATE_RATING) ?: 0
        secondRead = savedInstanceState?.getBoolean(STATE_SECOND_READ) ?: false
        setContent {
            MainActivityScreen(
                rating = rating,
                isRead = secondRead,
                onLike = { rating++ },
                onDislike = { rating-- },
                onOpenSecond = {
                    openSecondArticle.launch(
                        Intent(this, SecondActivity::class.java)
                            .putExtra(SecondActivity.EXTRA_READ, secondRead)
                    )
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_RATING, rating)
        outState.putBoolean(STATE_SECOND_READ, secondRead)
        super.onSaveInstanceState(outState)
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
        private const val TAG = "MainActivity"
        private const val STATE_RATING = "state_rating"
        private const val STATE_SECOND_READ = "state_second_read"
    }
}
