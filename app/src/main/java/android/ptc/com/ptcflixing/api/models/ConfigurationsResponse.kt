package android.ptc.com.ptcflixing.api.models


import com.google.gson.annotations.SerializedName

data class ConfigurationsResponse(
    @SerializedName("metadata")
    var metadata: Metadata?,
    @SerializedName("session")
    var session: Session?,
    @SerializedName("success")
    var success: Boolean?
) {

    data class Metadata(
        @SerializedName("currency")
        var currency: Currency?,
        @SerializedName("languages")
        var languages: List<Language?>?,
        @SerializedName("support")
        var support: Support?
    ) {

        data class Currency(
            @SerializedName("currency_symbol")
            var currencySymbol: String?,
            @SerializedName("decimals")
            var decimals: Int?,
            @SerializedName("decimals_sep")
            var decimalsSep: String?,
            @SerializedName("iso")
            var iso: String?,
            @SerializedName("position")
            var position: Int?,
            @SerializedName("thousands_sep")
            var thousandsSep: String?
        )


        data class Language(
            @SerializedName("code")
            var code: String?,
            @SerializedName("default")
            var default: Boolean?,
            @SerializedName("name")
            var name: String?
        )


        data class Support(
            @SerializedName("call_to_order_enabled")
            var callToOrderEnabled: Boolean?,
            @SerializedName("cs_email")
            var csEmail: String?,
            @SerializedName("phone_number")
            var phoneNumber: String?
        )
    }


    data class Session(
        @SerializedName("expire")
        var expire: Any?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("YII_CSRF_TOKEN")
        var yIICSRFTOKEN: String?
    )
}