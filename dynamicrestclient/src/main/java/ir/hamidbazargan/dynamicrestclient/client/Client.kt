package ir.hamidbazargan.dynamicrestclient.client

import ir.hamidbazargan.dynamicrestclient.base.Base

interface Client {

    fun cancelAllRequests()

    fun buildBase(
        baseUrl: String,
        defaultHeaders: HashMap<String, Any?>? = null
    ): Base

    companion object {
        fun builder(): ClientBuilder = ClientBuilderImplement()
    }
}