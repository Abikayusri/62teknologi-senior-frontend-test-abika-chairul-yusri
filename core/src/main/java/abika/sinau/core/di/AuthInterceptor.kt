package abika.sinau.core.di

import abika.sinau.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userToken = "Bearer ${BuildConfig.API_KEY}"

        val request = chain.request().newBuilder()
            .header("Authorization", userToken)
            .build()
        return chain.proceed(request)
    }
}