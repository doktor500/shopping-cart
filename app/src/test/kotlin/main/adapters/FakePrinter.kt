package main.adapters

import main.application.Printer

class FakePrinter : Printer {
    val messages = mutableListOf<String>()

    override fun print(text: String?) {
        text?.let { messages.add(it) }
    }
}