package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.content.MainActivityScreenState

class MainActivity : ComponentActivity() {

    private val viewModel: ArticleViewModel by viewModels()

    private val openSecondArticle =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val read = result.data?.getBooleanExtra(SecondActivity.EXTRA_READ, false) ?: false
            viewModel.setSecondRead(read)
            Log.d(TAG, "onActivityResult: second article read = $read")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContent {
            val state = MainActivityScreenState(viewModel)
            MainActivityScreen(
                state = state,
                onOpenSecond = {
                    openSecondArticle.launch(Intent(this, SecondActivity::class.java))
                }
            )
        }
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
    }
}