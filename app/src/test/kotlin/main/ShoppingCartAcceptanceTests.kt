package main

import main.presenters.ShoppingCartPresenter
import main.application.ShoppingCart
import main.domain.Amount
import main.domain.Product
import kotlin.test.Test
import kotlin.test.assertEquals

class ShoppingCartAcceptanceTests {
    @Test
    fun shoppingCartEmpty() {
        assertEquals("Shopping cart is empty!", ShoppingCartPresenter(ShoppingCart()).present())
    }

    @Test
    fun shoppingCartWithOneProductAdded() {
        val product = Product(name = "cornflakes", price = Amount(2.52.toBigDecimal()))
        val shoppingCart = ShoppingCart().add(product)

        assertEquals(
            """
            1 x cornflakes @ 2.52 each
            Subtotal = 2.52
            Tax = 0.32
            Total = 2.84
            """.trimIndent(),
            ShoppingCartPresenter(shoppingCart).present(),
        )
    }

    @Test
    fun shoppingCartWithDifferentProductsAdded() {
        val product1 = Product(name = "cornflakes", price = Amount(2.52.toBigDecimal()))
        val product2 = Product(name = "weetabix", price = Amount(9.98.toBigDecimal()))
        val shoppingCart = ShoppingCart().add(product = product1, quantity = 2).add(product = product2, quantity = 1)

        assertEquals(
            """
            2 x cornflakes @ 2.52 each
            1 x weetabix @ 9.98 each
            Subtotal = 15.02
            Tax = 1.88
            Total = 16.90
            """.trimIndent(),
            ShoppingCartPresenter(shoppingCart).present(),
        )
    }

    @Test
    fun shoppingCartWithSameProductAddedMultipleTimes() {
        val product1 = Product(name = "cornflakes", price = Amount(2.52.toBigDecimal()))
        val product2 = Product(name = "cornflakes", price = Amount(2.52.toBigDecimal()))
        val shoppingCart = ShoppingCart().add(product = product1).add(product = product2, quantity = 2)

        assertEquals(
            """
            3 x cornflakes @ 2.52 each
            Subtotal = 7.56
            Tax = 0.95
            Total = 8.51
            """.trimIndent(),
            ShoppingCartPresenter(shoppingCart).present()
        )
    }
}
