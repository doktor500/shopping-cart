package shoppingcart.application

import arrow.core.Either
import shoppingcart.domain.Product

class ShoppingCartService(private val productCatalogue: ProductCatalogue) {
    fun add(productName: String, to: ShoppingCart): Either<Error, ShoppingCart> {
        return productCatalogue.getPrice(productName).map { price -> to.add(Product(productName, price)) }
    }
}