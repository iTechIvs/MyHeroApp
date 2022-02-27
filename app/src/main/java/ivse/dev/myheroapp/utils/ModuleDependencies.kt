package ivse.dev.myheroapp.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ivse.dev.myheroapp.BuildConfig
import ivse.dev.myheroapp.services.MarvelApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (ActivityComponent::class)
object ModuleDependencies {
    //Base URL
    private const val BASE_URL = "https://gateway.marvel.com/"
    // Query keys that appended to request URL.
    private const val TIMESTAMP_QUERY_KEY = "ts"
    private const val API_KEY_QUERY_KEY = "apikey"
    private const val HASH_QUERY_KEY = "hash"
    //Public and Private keys (Safe un gradle, remember)
    private const val MARVEL_API_KEY = BuildConfig.MARVEL_API_KEY
    private const val MARVEL_PRIVATE_API_KEY = BuildConfig.MARVEL_PRIVATE_API_KEY

    @Singleton
    @Provides
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val currentTime = System.currentTimeMillis()
                val request = chain.request().newBuilder()
                val originalUrl = chain.request().url()
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter(TIMESTAMP_QUERY_KEY, currentTime.toString())
                    .addQueryParameter(API_KEY_QUERY_KEY,MARVEL_API_KEY)
                    .addQueryParameter(HASH_QUERY_KEY, Hash.createHash("$currentTime${MARVEL_PRIVATE_API_KEY}${MARVEL_API_KEY}"))
                    .build()

                request.url(newUrl)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelApiClient(retrofit: Retrofit): MarvelApiClient{
        return retrofit.create(MarvelApiClient::class.java)
    }
}