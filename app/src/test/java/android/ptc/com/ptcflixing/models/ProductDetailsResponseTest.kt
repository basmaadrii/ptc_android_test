package android.ptc.com.ptcflixing.models

import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ProductDetailsResponse

class ProductDetailsResponseTest {

    companion object {

        var metadata =   ProductDetailsResponse(
            sku = "1",
            name = "Samsung Galaxy S9",
            maxSavingPercentage = 30,
            price = 53996,
            specialPrice = 37990,
            brand = "Samsung",
            rating = ProductDetailsResponse.Rating(average = 4, ratingsTotal = 265),
            imageList = arrayOf(
                "https://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s9-.jpg",
                "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-1.jpg",
                "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-burgundy-red.jpg",
                "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-2.jpg",
                "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-2.jpg"
            ).toList(),
            summary = ProductDetailsResponse.Summary(
                shortDescription = "- 5.2 Inch 2.5D IPS Corning Gorilla Glass 3 Screen\\n - MTK6737T 1.5GHz Quad Core\\n - 3GB RAM + 32GB ROM\\n - 13 MP Back Camera + 16 MP Front Camera with Selfie Flash\\n -  Android 7.0 System\\n - 4000mAh Battery",
                description = "- 5.2 Inch 2.5D IPS Corning Gorilla Glass 3 Screen, 1280*720 Pixel- MTK6737T 1.5GHz Quad Core- Support Touch ID- 3GB RAM + 32GB ROM, Support 256GB TF Card Expansion- 13 MP Back Camera + 16 MP Front Camera with Selfie Flash- Amigo OS 4.0 ( Based On Android 7.0 System)- 4000mAh Battery.- Support Fingerprint Shutter, IR Remote Control, Image+, Ami Clone, Split Screen, Theme Park,Three Individual Slots etc.- Dual SIM: NANO SIM + NANO SIM + TF Card, three Card Slot.- Band: GSM 850/900/1800/1900MHz, WCDMA 850/900/1900/2100MHz, FDD-LTE B1/B3/B7/B8/B20, Support 2G & 3G & 4G LTE Network.-Language:English, German, Spanish, Italian, French, Portuguese, Thai, Arabic, Turkish, Vietnamese, Malay, Chinese (simplified), Chinese (traditional), And Etc..."
            ),
            sellerEntity = ProductDetailsResponse.SellerEntity(
                id = 52863,
                name = "iTechStor",
                deliveryTime = "Shipped from overseas. Delivered by Thursday 14 Jun"
            )

        )
        var response = BaseResponse(
            success = true,
            metadata = ProductDetailsResponse(
                sku = "1",
                name = "Samsung Galaxy S9",
                maxSavingPercentage = 30,
                price = 53996,
                specialPrice = 37990,
                brand = "Samsung",
                rating = ProductDetailsResponse.Rating(average = 4, ratingsTotal = 265),
                imageList = arrayOf(
                    "https://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s9-.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-1.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-burgundy-red.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-2.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s9-2.jpg"
                ).toList(),
                summary = ProductDetailsResponse.Summary(
                    shortDescription = "- 5.2 Inch 2.5D IPS Corning Gorilla Glass 3 Screen\\n - MTK6737T 1.5GHz Quad Core\\n - 3GB RAM + 32GB ROM\\n - 13 MP Back Camera + 16 MP Front Camera with Selfie Flash\\n -  Android 7.0 System\\n - 4000mAh Battery",
                    description = "- 5.2 Inch 2.5D IPS Corning Gorilla Glass 3 Screen, 1280*720 Pixel- MTK6737T 1.5GHz Quad Core- Support Touch ID- 3GB RAM + 32GB ROM, Support 256GB TF Card Expansion- 13 MP Back Camera + 16 MP Front Camera with Selfie Flash- Amigo OS 4.0 ( Based On Android 7.0 System)- 4000mAh Battery.- Support Fingerprint Shutter, IR Remote Control, Image+, Ami Clone, Split Screen, Theme Park,Three Individual Slots etc.- Dual SIM: NANO SIM + NANO SIM + TF Card, three Card Slot.- Band: GSM 850/900/1800/1900MHz, WCDMA 850/900/1900/2100MHz, FDD-LTE B1/B3/B7/B8/B20, Support 2G & 3G & 4G LTE Network.-Language:English, German, Spanish, Italian, French, Portuguese, Thai, Arabic, Turkish, Vietnamese, Malay, Chinese (simplified), Chinese (traditional), And Etc..."
                ),
                sellerEntity = ProductDetailsResponse.SellerEntity(
                    id = 52863,
                    name = "iTechStor",
                    deliveryTime = "Shipped from overseas. Delivered by Thursday 14 Jun"
                )

            ),
            messages = null
        )

    }

}