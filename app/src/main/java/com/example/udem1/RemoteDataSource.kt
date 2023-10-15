package com.example.udem1

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.Exception


interface RestCountriesApi {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountriesApi: RestCountriesApi = retrofit.create(RestCountriesApi::class.java)

suspend fun loadSvg (imageView: ImageView, url: String) {
    val imageLoader = ImageLoader.Builder(imageView.context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    imageView.load(url) {
        decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
    }
}