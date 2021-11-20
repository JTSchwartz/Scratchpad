package com.jtschwartz.scratchpad.config

object Config {
	const val TITLE = "Scratchpad"
	
	val FILE_PATH = "${java.io.File(
		com.jtschwartz.scratchpad.Scratchpad::class.java.protectionDomain.codeSource.location.toURI()).path.dropLastWhile { "[a-zA-Z.]".toRegex().matches(it.toString()) }}context.txt"
	
	const val WINDOW_HEIGHT_RATIO = 0.6
	const val WINDOW_WIDTH_RATIO = 0.5
}
