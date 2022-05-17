package ir.hamidbazargan.dynamicrestclient

import ir.hamidbazargan.dynamicrestclient.response.GeneralResponseModel
import okhttp3.ResponseBody
import retrofit2.Response

interface CustomParser<E: GeneralResponseModel> {

    fun parseData(
        data: String
    ): E?

    fun parseData(
        response: Response<ResponseBody?>
    ): E? { return null }
}