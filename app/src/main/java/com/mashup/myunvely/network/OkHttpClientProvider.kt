package com.mashup.myunvely.network

import android.content.Context
import android.os.Build
import android.os.StatFs
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mashup.myunvely.BuildConfig
import com.mashup.myunvely.data.sharedpreferences.MyunvelySharedPreferences
import com.mashup.myunvely.network.interceptors.AuthorizationInterceptor
import com.mashup.myunvely.network.interceptors.HeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

object OkHttpClientProvider {

    private const val TAG = "OkHttpClientProvider"

    val USER_AGENT = "Myunvely Android/${BuildConfig.VERSION_CODE} " +
            "(${Build.MODEL}; android ${Build.VERSION.RELEASE})"

    private const val CACHE_DIRECTORY_NAME = "okhttp_client_cache"
    private const val MIN_DISK_CACHE_SIZE: Long = 5L * 1024L * 1024L // 5MB
    private const val MAX_DISK_CACHE_SIZE: Long = 200L * 1024L * 1024L // 200MB

    private lateinit var cache: Cache

    lateinit var okHttpClient: OkHttpClient
        private set

    lateinit var okHttpClientWithoutAuthorization: OkHttpClient
        private set

    lateinit var okHttpClientForMediaUpload: OkHttpClient
        private set

    private object Interceptors {
        val httpLoggingInterceptor = HttpLoggingInterceptor { Log.d(TAG, it) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        val httpLoggingInterceptorBasic = HttpLoggingInterceptor { Log.d(TAG, it) }
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

        val headerInterceptor by lazy { HeaderInterceptor(USER_AGENT) }
    }

    fun init(context: Context, preferences: MyunvelySharedPreferences) {
        init(context, CACHE_DIRECTORY_NAME, ::calculateDiskCacheSize, preferences)
    }

    fun init(
        context: Context,
        cacheDirectoryName: String,
        calculateMaxSize: (File) -> Long,
        preferences: MyunvelySharedPreferences
    ) {
        cache = createCache(context, cacheDirectoryName, calculateMaxSize)
        okHttpClient =
            buildOkHttpClient(context, cache, preferences, Interceptors.httpLoggingInterceptor)
        okHttpClientWithoutAuthorization = buildOkHttpClient(
            context = context,
            cache = cache,
            preferences = preferences,
            loggingInterceptor = Interceptors.httpLoggingInterceptor,
            hasAuthorization = false
        )
        okHttpClientForMediaUpload = buildOkHttpClient(
            context = context,
            cache = cache,
            preferences = preferences,
            loggingInterceptor = Interceptors.httpLoggingInterceptorBasic,
            hasAuthorization = false
        )
    }

    private fun createCache(
        context: Context,
        cacheDirectoryName: String,
        calculateMaxSize: (File) -> Long
    ): Cache {
        val cacheDirectory = createCacheDirectory(context, cacheDirectoryName)
        val cache = Cache(cacheDirectory, calculateMaxSize(cacheDirectory))
        val sizeText = String.format("%.1fM", cache.size().toFloat() / 1000000f)

        // TODO 로그 확인
        Log.d(TAG, "Create cache for network, ${cacheDirectory.absolutePath}, ${sizeText}B")
        return cache
    }

    private fun createCacheDirectory(context: Context, cacheDirectoryName: String): File {
        val cacheDir = File(context.cacheDir, cacheDirectoryName)
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        return cacheDir
    }

    private fun calculateDiskCacheSize(directory: File): Long {
        val statFs = StatFs(directory.absolutePath)
        val available: Long = statFs.blockCountLong * statFs.blockSizeLong
        val size: Long = (available * 0.02).roundToLong()
        return max(MIN_DISK_CACHE_SIZE, min(MAX_DISK_CACHE_SIZE, size))
    }

    private fun buildOkHttpClient(
        context: Context,
        cache: Cache,
        preferences: MyunvelySharedPreferences,
        loggingInterceptor: HttpLoggingInterceptor,
        hasAuthorization: Boolean = true
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptors.headerInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
                addNetworkInterceptor(StethoInterceptor())
            }
            if (hasAuthorization) {
                addInterceptor(AuthorizationInterceptor(preferences))
            }
        }
        .cache(cache)
        .build()
}
