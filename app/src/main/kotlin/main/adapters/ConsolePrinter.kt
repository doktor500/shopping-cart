package main.adapters

import main.application.Printer

class ConsolePrinter : Printer {
    override fun print(text: String?) {
        text?.let { println(it) }
    }
}