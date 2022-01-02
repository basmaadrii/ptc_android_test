package android.ptc.com.ptcflixing.utils

import android.ptc.com.ptcflixing.data.model.Currency
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class FloatExtTest {
    private lateinit var currency: Currency
    private lateinit var stringInCurrencyFormat: String
    private var float: Float = 0F

    @Before fun setup() {
        float = 49500.59F
        currency = Currency(
            iso = "NGN",
            currencySymbol = "\u20a6",
            position = 0,
            decimals = 2,
            thousandSeparator = ",",
            decimalSeparator = "."
        )
        stringInCurrencyFormat = "₦49,500.59"
    }

    @Test
    @Throws(Exception::class)
    fun floatToCurrencyFormatString_isCorrect() {
        val stringFormatted = float.toCurrencyFormat(currency)
        assertEquals(stringInCurrencyFormat, stringFormatted)
    }
}