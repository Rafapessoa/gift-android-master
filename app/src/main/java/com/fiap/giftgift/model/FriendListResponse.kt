package com.fiap.giftgift.model

data class Friend (
        val name:  String = "",
        val email: String = "",
        val phone: String = "",
        val gifts: ArrayList<String> = ArrayList()
)