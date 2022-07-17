package main.adapters

import arrow.core.Either
import main.application.ProductCatalogue
import main.domain.Amount
import main.domain.Product

class FakeProductCatalogue : ProductCatalogue {
    private val products = listOf(
        Product(name = "cheerios", price = Amount(8.43.toBigDecimal())),
        Product(name = "cornflakes", price = Amount(2.52.toBigDecimal())),
        Product(name = "frosties", price = Amount(4.99.toBigDecimal())),
        Product(name = "shreddies", price = Amount(4.68.toBigDecimal())),
        Product(name = "weetabix", price = Amount(9.98.toBigDecimal()))
    )

    override fun getPrice(productName: String): Either<Error, Amount> {
        return when (val product = findProductBy(productName)) {
            null -> Either.Left(Error("Product with name $productName was not found"))
            else -> Either.Right(product.price)
        }
    }

    private fun findProductBy(productName: String): Product? = products.find { it.name == productName.lowercase() }
}