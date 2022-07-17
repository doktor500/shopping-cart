package main

import arrow.core.handleError
import main.adapters.ConsolePrinter
import main.adapters.ProductCatalogueApi
import main.presenters.ShoppingCartPresenter
import main.application.ShoppingCartService
import main.application.Printer
import main.application.ShoppingCart

class ShoppingCartApplication(
    private val shoppingCartService: ShoppingCartService = ShoppingCartService(ProductCatalogueApi()),
    private val printer: Printer = ConsolePrinter()
) {

    private var shoppingCart = ShoppingCart()

    fun add(productName: String) {
        shoppingCartService.add(productName, to = shoppingCart)
            .map { shoppingCart = it }
            .map { printer.print(ShoppingCartPresenter(shoppingCart).present()) }
            .handleError { error -> printer.print(error.message) }
    }
}