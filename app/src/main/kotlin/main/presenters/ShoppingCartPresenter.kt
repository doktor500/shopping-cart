package main.presenters

import main.application.ShoppingCart
import main.domain.Product

class ShoppingCartPresenter(private val shoppingCart: ShoppingCart) {
    fun present(): String {
        return when {
            items.isEmpty() -> "Shopping cart is empty!"
            else -> summary().joinToString(System.lineSeparator())
        }
    }

    private val items get() = shoppingCart.items

    private fun summary(): List<String> = items().plus(subtotal()).plus(tax()).plus(total())
    private fun subtotal(): String = "Subtotal = ${shoppingCart.subtotal()}"
    private fun tax(): String = "Tax = ${shoppingCart.tax()}"
    private fun total(): String = "Total = ${shoppingCart.total()}"
    private fun items(): List<String> = items.map { "${it.quantity} x ${ProductPresenter(it.product).present()}" }
}

private class ProductPresenter(private val product: Product) {
    fun present(): String = "${product.name} @ ${product.price} each"
}