package com.jtschwartz.scratchpad

import com.jtschwartz.scratchpad.view.MainView
import tornadofx.App
import tornadofx.launch

fun main() {
	launch<Scratchpad>()
}

class Scratchpad: App(MainView::class, Styles::class)
