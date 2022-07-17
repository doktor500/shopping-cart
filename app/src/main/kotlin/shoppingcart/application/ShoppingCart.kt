package shoppingcart.application

import shoppingcart.domain.Amount
import shoppingcart.domain.Product

data class ShoppingCart(val items: List<ShoppingCartItem> = emptyList()) {
    companion object {
        private val TAX = Amount(0.125.toBigDecimal())
    }

    fun add(product: Product, quantity: Int = 1): ShoppingCart = ShoppingCart(addToShoppingCartItems(product, quantity))
    fun subtotal(): Amount = items.sumOf { item -> item.subtotal() }
    fun tax(): Amount = items.sumOf { item -> item.subtotal() * TAX }
    fun total(): Amount = subtotal() + tax()

    private fun addToShoppingCartItems(product: Product, quantity: Int): List<ShoppingCartItem> {
        return when (val existingItem = findItemBy(product)) {
            null -> items.plus(ShoppingCartItem(product, quantity))
            else -> items.minus(existingItem).plus(existingItem.plus(quantity))
        }
    }

    private fun findItemBy(product: Product): ShoppingCartItem? = items.find { item -> item.product == product }
}

private inline fun Iterable<ShoppingCartItem>.sumOf(selector: (ShoppingCartItem) -> Amount): Amount {
    return this.map { item -> selector(item) }.reduce { sum, amount -> sum + amount }
}