package com.jtschwartz.scratchpad.utils

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

fun KeyEvent.isEnter(): Boolean {
	return this.code == KeyCode.ENTER
}

fun KeyEvent.ifEnter(block: () -> Unit) {
	if (this.isEnter()) {
		block()
	}
}
