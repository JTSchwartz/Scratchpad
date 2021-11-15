package com.jtschwartz.scratchpad

import javafx.scene.paint.Color
import tornadofx.*

class Styles: Stylesheet() {
	companion object {
		val root by cssclass()
		val jetBrainsMono = loadFont("/fonts/JetBrainsMono-Regular.ttf", 10.0)!!
	}
	
	init {
		val primary = Color.BLACK
		
		root {
			baseColor = primary
			
			textInput {
				font = jetBrainsMono
				backgroundColor += Color.gray(0.5)
			}
			
			checkBox {
				baseColor = Color.gray(0.5)
				textFill = Color.WHITE
			}
		}
	}
}
