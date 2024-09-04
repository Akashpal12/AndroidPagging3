package com.akashpal.gcrg.mvvm.models

data class RequestContainer(
    val securityToken: String,
    val deviceId: String,
    val latLong: String,
    val appVersion: String,
    val requestSource: String
)