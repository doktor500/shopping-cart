package main

import main.adapters.FakePrinter
import kotlin.test.Test
import kotlin.test.assertEquals

class ShoppingCartSystemTests {

    @Test fun displaysShoppingCart() {
        val printer = FakePrinter()
        val shoppingCartApp = ShoppingCartApplication(printer = printer)
        val productName = "cornflakes"
        shoppingCartApp.add(productName)

        assertEquals(
            """
            1 x cornflakes @ 2.52 each
            Subtotal = 2.52
            Tax = 0.32
            Total = 2.84
            """.trimIndent(),
            printer.messages.first(),
        )
    }

    @Test fun displaysErrorDetails() {
        val printer = FakePrinter()
        val shoppingCartApp = ShoppingCartApplication(printer = printer)
        val productName = "invalid-product"
        shoppingCartApp.add(productName)

        assertEquals("Product with name invalid-product was not found", printer.messages.first())
    }
}
