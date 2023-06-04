package com.example.cleaningapp.cleaner.uistate

import java.io.Serializable

    data class Work
        (
        var status: Int,
        val imageId: Int = 0,
        val orderId: String = "",
        val date: String = "",
        val time: String = "",
        val address: String = "",
        val livingRoomSize:  Double = 0.0,
        val kitchenSize:  Double = 0.0,
        val bathroomSize: Double = 0.0,
        val bedroomSize:  Double = 0.0,
        val location: String ="",
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
//

//
