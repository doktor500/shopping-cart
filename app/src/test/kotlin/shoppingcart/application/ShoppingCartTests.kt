package shoppingcart.application

import shoppingcart.domain.Amount
import shoppingcart.domain.Product
import shoppingcart.domain.amount
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ShoppingCartTests {
    @ParameterizedTest
    @MethodSource("subtotal")
    fun calculateSubtotal(quantity: Int, product: Product, expectedSubtotal: Amount) {
        val shoppingCart = ShoppingCart().add(product = product, quantity = quantity)
        assertEquals(expectedSubtotal, shoppingCart.subtotal())
    }

    @ParameterizedTest
    @MethodSource("tax")
    fun calculateTax(product: Product, expectedTax: Amount) {
        val shoppingCart = ShoppingCart().add(product)
        assertEquals(expectedTax, shoppingCart.tax())
    }

    @ParameterizedTest
    @MethodSource("total")
    fun calculateTotal(product: Product, expectedTotal: Amount) {
        val shoppingCart = ShoppingCart().add(product)
        assertEquals(expectedTotal, shoppingCart.total())
    }

    companion object {
        @JvmStatic
        fun subtotal() = listOf(
            Arguments.of(1, product(price = amount("4.99")), amount("4.99")),
            Arguments.of(2, product(price = amount("7.43")), amount("14.86"))
        )

        @JvmStatic
        fun tax() = listOf(
            Arguments.of(product(price = amount("4.99")), amount("0.62")),
            Arguments.of(product(price = amount("7.43")), amount("0.93"))
        )

        @JvmStatic
        fun total() = listOf(
            Arguments.of(product(price = amount("4.99")), amount("5.61")),
            Arguments.of(product(price = amount("7.43")), amount("8.36"))
        )

        private fun product(price: Amount) = Product("A product", price)
    }
}