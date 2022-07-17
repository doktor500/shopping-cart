package shoppingcart.adapters

import arrow.core.Either
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import shoppingcart.application.ProductCatalogue
import shoppingcart.domain.Amount
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.math.BigDecimal

class ProductCatalogueApi : ProductCatalogue {
    companion object {
        private const val BASE_URL = "https://equalexperts.github.io/backend-take-home-test-data"
    }

    private val client = OkHttpClient()
    private val objectMapper = jacksonObjectMapper()

    override fun getPrice(productName: String): Either<Error, Amount> {
        val request = Request.Builder().url("$BASE_URL/${productName.lowercase()}.json").build()
        client.newCall(request).execute().use { response -> return handleResponse(response, productName) }
    }

    private fun handleResponse(response: Response, productName: String): Either<Error, Amount> {
        return when (response.code) {
            404 -> Either.Left(Error("Product with name $productName was not found"))
            else -> parseResponseBody(response.body)
        }
    }

    private fun parseResponseBody(responseBody: ResponseBody?): Either<Error, Amount> {
        return when (responseBody) {
            null -> Either.Left(Error("The Product catalogue API returned an invalid response"))
            else -> Either.Right(Amount(priceFrom(responseBody.string())))
        }
    }

    private fun priceFrom(json: String) = productFrom(json).price
    private fun productFrom(json: String) = objectMapper.readValue<Product>(json)
}

private data class Product(val title: String, val price: BigDecimal)