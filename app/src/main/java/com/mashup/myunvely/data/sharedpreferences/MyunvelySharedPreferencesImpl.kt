package com.mashup.myunvely.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import io.reactivex.processors.BehaviorProcessor

object MyunvelySharedPreferencesImpl : MyunvelySharedPreferences {

    private lateinit var sharedPreferences: SharedPreferences

    /**
     * 주석사항은 참고사항으로 넣어놨습니다
     * 주석사항을 참고로 해서 처음 코드를 작성하면 주석은 모두 지워주세요.
     */

    private const val KEY_AUTH_TOKEN = "auth_token"
    private val currentAuthTokenProcessor= BehaviorProcessor.create<String>()

    fun init(applicationContext: Context) {
        sharedPreferences =
            applicationContext.getSharedPreferences("group_preferences", Context.MODE_PRIVATE)

        sharedPreferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            emitValue(sharedPreferences, key)
        }

        emitInitialValue()
    }

    private fun emitInitialValue() {
        emitValue(sharedPreferences, KEY_AUTH_TOKEN)
    }

    private fun emitValue(sharedPreferences: SharedPreferences, key: String) {
        when (key) {
            KEY_AUTH_TOKEN ->
                currentAuthTokenProcessor.offer(sharedPreferences.getString(key, ""))
        }
    }

    override fun saveAuthToken(token: String) {
        sharedPreferences.edit { putString(KEY_AUTH_TOKEN, token) }
    }

    override fun getAuthToken(): String? = currentAuthTokenProcessor.value
}
