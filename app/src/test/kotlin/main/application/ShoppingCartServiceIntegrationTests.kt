package main.application

import arrow.core.computations.ResultEffect.bind
import arrow.core.handleError
import main.adapters.FakeProductCatalogue
import main.adapters.ProductCatalogueApi
import main.domain.Product
import main.domain.amount
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ShoppingCartServiceIntegrationTests {

    @ParameterizedTest
    @MethodSource("validProducts")
    fun findsProductPriceAndAddsProductToShoppingCart(productCatalogue: ProductCatalogue, product: Product) {
        val shoppingCartService = ShoppingCartService(productCatalogue)
        val shoppingCart = shoppingCartService.add(product.name, to = ShoppingCart())
        val expectedShoppingCart = ShoppingCart().add(Product(product.name, product.price))

        assertEquals(expectedShoppingCart, shoppingCart.bind())
    }

    @ParameterizedTest
    @MethodSource("invalidProducts")
    fun returnsErrorWhenProductIsNotFound(productCatalogue: ProductCatalogue, productName: String) {
        val shoppingCartService = ShoppingCartService(productCatalogue)
        val error = shoppingCartService.add(productName, to = ShoppingCart())

        error.handleError { assertEquals("Product with name $productName was not found", it.message) }
    }

    companion object {
        @JvmStatic
        fun validProducts() = listOf(
            Arguments.of(FakeProductCatalogue(), Product("Cornflakes", amount("2.52"))),
            Arguments.of(FakeProductCatalogue(), Product("cheerios", amount("8.43"))),
            Arguments.of(ProductCatalogueApi(), Product("Cornflakes", amount("2.52"))),
            Arguments.of(ProductCatalogueApi(), Product("cheerios", amount("8.43")))
        )

        @JvmStatic
        fun invalidProducts() = listOf(
            Arguments.of(FakeProductCatalogue(), "invalid-product"),
            Arguments.of(ProductCatalogueApi(), "invalid-product")
        )
    }
}