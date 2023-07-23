package com.exequielvr.myappsprint.api

import com.exequielvr.myappsprint.model.ItemDetail
import com.exequielvr.myappsprint.model.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface AppApi {

    @GET("products/")
    suspend fun fecthItemsList(): Response<List<Items>>

    @GET("products/{id}")
    suspend fun fechItemDetail(@Path("id") id: String): Response<ItemDetail>
}