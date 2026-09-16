package ru.urfu.droidpractice1

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs =
        application.getSharedPreferences(SecondActivity.PREFS_NAME, Context.MODE_PRIVATE)

    private val _rating = MutableStateFlow(prefs.getInt(KEY_RATING, 0))
    val rating: StateFlow<Int> = _rating.asStateFlow()

    private val _secondRead = MutableStateFlow(prefs.getBoolean(SecondActivity.KEY_READ, false))
    val secondRead: StateFlow<Boolean> = _secondRead.asStateFlow()

    fun like() {
        _rating.update {
            val new = it + 1
            prefs.edit().putInt(KEY_RATING, new).apply()
            new
        }
    }

    fun dislike() {
        _rating.update {
            val new = it - 1
            prefs.edit().putInt(KEY_RATING, new).apply()
            new
        }
    }

    fun setSecondRead(read: Boolean) {
        _secondRead.value = read
        prefs.edit().putBoolean(SecondActivity.KEY_READ, read).apply()
    }

    companion object {
        private const val KEY_RATING = "article_rating"
    }
}