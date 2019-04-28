package com.fiap.giftgift.model

import com.google.gson.annotations.SerializedName

data class FriendListResponse(@SerializedName("content")
                           val friends: List<Friend>)

data class Friend(
        @SerializedName("number") val numero: String?,
        @SerializedName("name") val nome: String?,
        @SerializedName("imageURL") val imagem: String?

)