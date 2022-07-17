package shoppingcart.application

import arrow.core.Either
import shoppingcart.domain.Amount

interface ProductCatalogue {
    fun getPrice(productName: String): Either<Error, Amount>
}