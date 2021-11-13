package com.jtschwartz.scratchpad

import javafx.scene.paint.Color
import tornadofx.*

class Styles: Stylesheet() {
	companion object {
		val root by cssclass()
		val textArea by cssclass()
		val jetBrainsMono = loadFont("/fonts/JetBrainsMono-Regular.ttf", 10.0)!!
	}
	
	init {
		textArea {
			font = jetBrainsMono
			borderColor += box(Color.GRAY, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT)
		}
		
		textArea and focused {
			backgroundColor += c(Color.WHITE.css)
		}
	}
}
