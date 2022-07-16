package main.adapters

import main.application.ShoppingCart
import main.domain.Product

class ShoppingCartPresenter(private val shoppingCart: ShoppingCart) {
    fun print(): String {
        return when {
            items.isEmpty() -> "Shopping cart is empty!"
            else -> summary().joinToString(System.lineSeparator())
        }
    }

    private val items get() = this.shoppingCart.items

    private fun summary(): List<String> = this.items().plus(this.subtotal()).plus(this.tax()).plus(this.total())
    private fun subtotal(): String = "Subtotal = ${this.shoppingCart.subtotal()}"
    private fun tax(): String = "Tax = ${this.shoppingCart.tax()}"
    private fun total(): String = "Total = ${this.shoppingCart.total()}"
    private fun items(): List<String> = items.map { "${it.quantity} x ${ProductPresenter(it.product).print()}" }
}

private class ProductPresenter(private val product: Product) {
    fun print(): String = "${product.name} @ ${product.price} each"
}