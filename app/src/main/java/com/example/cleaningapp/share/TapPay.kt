package com.example.cleaningapp.share

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.cleaningapp.R
import com.google.android.gms.wallet.TransactionInfo
import com.google.android.gms.wallet.WalletConstants
import tech.cherri.tpdirect.api.*

class TapPay(context: Context) {
    private val myTag = "TAG_${javaClass.simpleName}"
    private val requestCode = 101
    private lateinit var tpdGooglePay: TPDGooglePay
    private var context: Context

    // 測試環境網址
    private val sandbox = "https://sandbox.tappaysdk.com/"

    // 取得Prime後跟TapPay確定支付的連線網址
    private val primeUrl = "tpc/payment/pay-by-prime"

    // 信用卡種類
    private val cardTypes = arrayOf(
        TPDCard.CardType.Visa,
        TPDCard.CardType.MasterCard,
        TPDCard.CardType.JCB,
        TPDCard.CardType.AmericanExpress
    )

    init {
        this.context = context
    }

    fun prepareGooglePay(price: Int) {
        TPDSetup.initInstance(
            context,
            context.getString(R.string.TapPay_AppID).toInt(),
            context.getString(R.string.TapPay_AppKey),
            TPDServerType.Sandbox
        )
        // 建立商店物件
        val tpdMerchant = TPDMerchant()
        // 設定商店名稱
        tpdMerchant.merchantName = context.getString(R.string.TapPay_MerchantName)
        // 設定允許的信用卡種類
        tpdMerchant.supportedNetworks = cardTypes
        // 建立消費者物件，並設定需要填寫項目
        val tpdConsumer = TPDConsumer()
        // 不需要電話號碼
        tpdConsumer.isPhoneNumberRequired = false
        // 不需要運送地址
        tpdConsumer.isShippingAddressRequired = false
        // 不需要Email
        tpdConsumer.isEmailRequired = false
        tpdGooglePay = TPDGooglePay(context as Activity?, tpdMerchant, tpdConsumer)
        // 檢查使用者裝置是否支援Google Pay
        tpdGooglePay.isGooglePayAvailable { isReadyToPay, message ->
            Log.d(myTag, "Pay with Google availability: ${isReadyToPay}; message: $message")
            if (isReadyToPay) {
                // 跳出user資訊視窗讓user確認，確認後會呼叫onActivityResult()
                tpdGooglePay.requestPayment(
                    TransactionInfo.newBuilder()
                        .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                        .setTotalPrice(price.toString()) // 消費總金額
                        .setCurrencyCode("TWD") // 設定幣別
                        .build(), requestCode
                )
            }
        }
    }
}