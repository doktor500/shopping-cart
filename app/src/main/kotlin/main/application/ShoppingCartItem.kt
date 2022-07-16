package main.application

import main.domain.Amount
import main.domain.Product

data class ShoppingCartItem(val product: Product, val quantity: Int = 1) {
    fun subtotal(): Amount = product.price * quantity
    fun plus(quantity: Int): ShoppingCartItem = ShoppingCartItem(product, this.quantity + quantity)
}