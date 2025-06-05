package com.vkbao.landingapi.currencyPicker

import android.net.Uri

interface CurrencyPickerDeepLinkProvider {
    fun getDeepLink(): Uri?
}