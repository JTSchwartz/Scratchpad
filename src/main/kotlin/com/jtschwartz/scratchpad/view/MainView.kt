package com.jtschwartz.scratchpad.view

import com.jtschwartz.scratchpad.Styles
import com.jtschwartz.scratchpad.config.Constants
import javafx.stage.Screen
import tornadofx.*

class MainView : View(Constants.TITLE) {
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
    
    init {
    	with(root) {
            val screenHeight = Screen.getPrimary().bounds.height
            val screenWidth = Screen.getPrimary().bounds.width
            
            println(Constants.WINDOW_HEIGHT_RATIO)
            println(screenWidth * Constants.WINDOW_WIDTH_RATIO)
            
            prefHeight = screenHeight * Constants.WINDOW_HEIGHT_RATIO
            prefWidth = screenWidth * Constants.WINDOW_WIDTH_RATIO
        }
    }
}
