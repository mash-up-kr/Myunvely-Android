package com.mashup.myunvely.di

import android.content.Context
import android.os.StatFs
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mashup.myunvely.BuildConfig
import com.mashup.myunvely.network.OkHttpClientProvider
import com.mashup.myunvely.network.interceptors.AuthorizationInterceptor
import com.mashup.myunvely.network.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Qualifier
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpClientWithoutAuthorization

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MediaUploadOkHttpClient

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val TAG = "NetworkModule"

    private const val CACHE_DIRECTORY_NAME = "okhttp_client_cache"
    private const val MIN_DISK_CACHE_SIZE: Long = 5L * 1024L * 1024L // 5MB
    private const val MAX_DISK_CACHE_SIZE: Long = 200L * 1024L * 1024L // 200MB

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDirectory =
            createCacheDirectory(context)
        val cache = Cache(
            cacheDirectory,
            calculateMaxSize(cacheDirectory)
        )
        val sizeText = String.format("%.1fM", cache.size().toFloat() / 1000000f)

        // TODO 로그 확인
        Log.d(TAG, "Create cache for network, ${cacheDirectory.absolutePath}, ${sizeText}B")
        return cache
    }

    private fun createCacheDirectory(context: Context): File {
        val cacheDir = File(
            context.cacheDir,
            CACHE_DIRECTORY_NAME
        )
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        return cacheDir
    }

    private fun calculateMaxSize(directory: File): Long {
        val statFs = StatFs(directory.absolutePath)
        val available: Long = statFs.blockCountLong * statFs.blockSizeLong
        val size: Long = (available * 0.02).roundToLong()
        return max(
            MIN_DISK_CACHE_SIZE, min(
                MAX_DISK_CACHE_SIZE, size
            )
        )
    }

    @DefaultOkHttpClient
    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient = buildOkHttpClient(
        cache = cache,
        loggingInterceptor = Interceptors.httpLoggingInterceptor,
        authorizationInterceptor = authorizationInterceptor
    )

    @OkHttpClientWithoutAuthorization
    @Provides
    fun provideOkHttpClientWithoutAuthorization(cache: Cache): OkHttpClient = buildOkHttpClient(
        cache = cache,
        loggingInterceptor = Interceptors.httpLoggingInterceptor,
        authorizationInterceptor = null
    )

    private fun buildOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor?
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptors.headerInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
                addNetworkInterceptor(StethoInterceptor())
            }
            authorizationInterceptor?.let { addInterceptor(it) }
        }
        .cache(cache)
        .build()

    private object Interceptors {
        val httpLoggingInterceptor = HttpLoggingInterceptor { Log.d(TAG, it) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }

        val headerInterceptor by lazy { HeaderInterceptor(OkHttpClientProvider.USER_AGENT) }
    }
}