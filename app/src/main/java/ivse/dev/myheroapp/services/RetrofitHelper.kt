package ivse.dev.myheroapp.services

import ivse.dev.myheroapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class RetrofitHelper {
    companion object{
        //Base URL
        private const val BASE_URL = "https://gateway.marvel.com/"
        // Query keys that appended to request URL.
        private const val TIMESTAMP_QUERY_KEY = "ts"
        private const val API_KEY_QUERY_KEY = "apikey"
        private const val HASH_QUERY_KEY = "hash"
        //Public and Private keys (Safe un gradle, remember)
        private const val MARVEL_API_KEY = BuildConfig.MARVEL_API_KEY
        private const val MARVEL_PRIVATE_API_KEY = BuildConfig.MARVEL_PRIVATE_API_KEY

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val currentTime = System.currentTimeMillis()
                    val request = chain.request().newBuilder()
                    val originalUrl = chain.request().url()
                    val newUrl = originalUrl.newBuilder()
                        .addQueryParameter(TIMESTAMP_QUERY_KEY, currentTime.toString())
                        .addQueryParameter(API_KEY_QUERY_KEY,MARVEL_API_KEY)
                        .addQueryParameter(HASH_QUERY_KEY, createHash("$currentTime${MARVEL_PRIVATE_API_KEY}${MARVEL_API_KEY}"))
                        .build()

                    request.url(newUrl)
                    return@addInterceptor chain.proceed(request.build())
                }
                .build()
        }

        private fun createHash(input: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            return BigInteger(1, md5.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}