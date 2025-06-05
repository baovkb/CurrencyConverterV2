package navigation.deeplink

import android.net.Uri
import com.vkbao.landingapi.currencyPicker.CurrencyPickerDeepLinkProvider
import androidx.core.net.toUri

class CurrencyPickerDeepLinkProviderImpl(

): CurrencyPickerDeepLinkProvider {
    override fun getDeepLink(): Uri? {
        return getSupportedDeepLinks()[0].uriPattern?.toUri()
    }
}