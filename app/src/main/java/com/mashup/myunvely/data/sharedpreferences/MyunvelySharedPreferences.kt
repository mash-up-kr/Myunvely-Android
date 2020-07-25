package com.mashup.myunvely.data.sharedpreferences

interface MyunvelySharedPreferences{
     fun saveAuthToken(token: String)
     fun getAuthToken(): String?
}
