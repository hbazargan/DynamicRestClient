package com.example.clienttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.annotations.SerializedName
import ir.hamidbazargan.dynamicrestclient.CustomParser
import ir.hamidbazargan.dynamicrestclient.client.Client
import ir.hamidbazargan.dynamicrestclient.response.GeneralResponseModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Client
            .builder()
            .withDebugMode(true)
            .build()
            .buildBase(
                "https://bw-api.zarebin.ir",
                hashMapOf("TEST" to "test")
            ).also {
                GlobalScope.launch {
                    it.postMethod(Response::class.java, "/reg")
                        .addHeader("test", "TEST")
                        .setPostBody(Test("test"))
                        .getResponse()
                }
            }
        Client
            .builder()
            .withDebugMode(true)
            .build()
            .buildBase("http://www.zarebin.ir").also {
                GlobalScope.launch {
                    it.getMethod(GeneralResponseModel::class.java)
                        .setCustomParser(object : CustomParser<GeneralResponseModel> {
                            override fun parseData(data: String): GeneralResponseModel {
                                return EmptyResponse()
                            }

                            override fun parseData(response: retrofit2.Response<ResponseBody?>): GeneralResponseModel {
                                Log.d("TEST", response.raw().toString())
                                return EmptyResponse()
                            }
                        })
                        .getResponse()
                }
            }
    }
}

class Response(
    @SerializedName("Test")
    val test: String?
) : GeneralResponseModel()

class Test(
    @SerializedName("device_id")
    val test: String?
)

class EmptyResponse : GeneralResponseModel()