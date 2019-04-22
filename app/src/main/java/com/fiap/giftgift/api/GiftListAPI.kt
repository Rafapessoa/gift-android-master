package com.fiap.giftgift.api


import com.fiap.giftgift.model.GiftListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiftListAPI {
    @GET("/api/pokemon")
    fun buscar(@Query("size") size: Int) : Observable<GiftListResponse>


}