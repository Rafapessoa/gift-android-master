package com.fiap.giftgift.api


import com.fiap.giftgift.model.FriendListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendListAPI {
    @GET("/api/pokemon")
    fun buscar(@Query("size") size: Int) : Observable<FriendListResponse>


}