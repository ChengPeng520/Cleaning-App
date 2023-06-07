package com.example.cleaningapp.cleaner.uistate

import com.example.cleaningapp.share.OrderUtil
import java.io.Serializable

    data class Work
        (
        var status: Int,
        val imageId: Int = 0,
        val orderId: Int = 0 ,
        val date: String = "",
        val time: String = "",
        val address: String = "",
        val livingRoomSize:  Int = 0,
        val kitchenSize:  Int = 0,
        val bathroomSize: Int = 0,
        val bedroomSize:  Int = 0,
        val areaCity: String ="",
        val areaDistrict: String ="",
        val notes: String = "",
        val price: String = "",
    ):Serializable {
        val livingRoomSizeString: String
            get() = livingRoomSize.toString()
        val kitchenSizeString: String
            get() = kitchenSize.toString()
        val bathroomSizeString: String
            get() = bathroomSize.toString()
        val bedroomSizeString: String
            get() = bedroomSize.toString()
    }