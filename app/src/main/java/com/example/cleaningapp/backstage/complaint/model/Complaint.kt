package com.example.cleaningapp.backstage.complaint.model

class Complaint(
    var id: Int,
    var orderId: String,
    var applier: String,
    var createTime: String,
    var cleaner: String,
    var status: String,
    var updateTime: String,
    var photo1: Int,
    var photo2: Int,
    var photo3: Int,
    var content: String,
    var remark: String
    ) : java.io.Serializable