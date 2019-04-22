package com.fiap.giftgift.model

import com.google.gson.annotations.SerializedName

data class GiftListResponse(@SerializedName("content")
                           val gifts: List<Gift>)

data class Gift(
        @SerializedName("number") val numero: String,
        @SerializedName("name") val nome: String,
        @SerializedName("imageURL") val imagem: String

)