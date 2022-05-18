package ir.hamidbazargan.dynamicrestclient.client

import ir.hamidbazargan.dynamicrestclient.ApiInterface
import ir.hamidbazargan.dynamicrestclient.base.BaseImplement
import ir.hamidbazargan.dynamicrestclient.base.Base
import okhttp3.*
import retrofit2.Converter
import kotlin.collections.HashMap

internal class ClientImplement(
    authenticator: Authenticator?,
    interceptors: List<Interceptor>,
    private val converterFactories: List<Converter.Factory>,
    cache: Cache?,
    debugMode: Boolean
) : Client {

    private val restClient: RestClient by lazy {
        RestClient()
    }

    private val okHttpClient: OkHttpClient by lazy {
        restClient.getOkHttp(
            authenticator,
            interceptors,
            cache,
            debugMode
        )
    }

    private var restInterface: ApiInterface? = null

    fun getRestClient(baseUrl: String): ApiInterface {
        return restInterface ?: restClient.createRetrofitClient(
            okHttpClient,
            baseUrl,
            converterFactories
        ).create(ApiInterface::class.java).also {
            restInterface = it
        }
    }

    override fun cancelAllRequests() {
        okHttpClient.dispatcher.cancelAll()
    }

    override fun buildBase(
        baseUrl: String,
        defaultHeaders: HashMap<String, Any?>?
    ): Base {
        return BaseImplement(
            this,
            baseUrl,
            defaultHeaders,
        )
    }
}