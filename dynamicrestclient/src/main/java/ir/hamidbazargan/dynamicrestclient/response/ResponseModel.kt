package ir.hamidbazargan.dynamicrestclient.response

data class ResponseModel<E: GeneralResponseModel>(
    val httpCode : Int,
    var rawResponse: String,
    val data: E
)