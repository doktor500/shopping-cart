package main.application

import arrow.core.Either
import main.domain.Amount

interface ProductCatalogue {
    fun getPrice(productName: String): Either<Error, Amount>
}