package ir.hamidbazargan.dynamicrestclient.client

import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor

interface ClientBuilder {

    fun withAuthenticator(authenticator: Authenticator): ClientBuilder

    fun withInterceptors(interceptors: List<Interceptor>): ClientBuilder

    fun withInterceptor(interceptor: Interceptor): ClientBuilder

    fun withDebugMode(debugMode: Boolean): ClientBuilder

    fun withCache(cache: Cache): ClientBuilder

    fun build(): Client
}