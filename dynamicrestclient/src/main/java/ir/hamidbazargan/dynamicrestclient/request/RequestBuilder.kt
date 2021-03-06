package ir.hamidbazargan.dynamicrestclient.request

import ir.hamidbazargan.dynamicrestclient.CustomParser
import ir.hamidbazargan.dynamicrestclient.response.GeneralResponseModel
import ir.hamidbazargan.dynamicrestclient.response.ResponseModel

interface RequestBuilder<E : GeneralResponseModel> {

    fun setNotEncoded(): RequestBuilder<E>

    fun setPostBody(postBody: Any): RequestBuilder<E>

    fun setResponseModel(responseModel: Class<E>): RequestBuilder<E>

    fun setCustomParser(customParser: CustomParser<E>?): RequestBuilder<E>

    fun addBodyParameters(requestBodyHashMap: HashMap<String, Any?>?): RequestBuilder<E>

    fun addBodyParameter(
        key: String,
        value: Any
    ): RequestBuilder<E>

    fun addHeaders(headersMap: HashMap<String, Any?>?): RequestBuilder<E>

    fun addHeader(key: String, value: Any): RequestBuilder<E>

    fun addQueryParameters(queryParameters: HashMap<String, Any?>?): RequestBuilder<E>

    fun addQueryParameter(
        key: String,
        value: Any
    ): RequestBuilder<E>

    fun getResponse(): ResponseModel<E>
}