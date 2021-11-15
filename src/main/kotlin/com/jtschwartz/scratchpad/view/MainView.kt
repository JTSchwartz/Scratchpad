package com.jtschwartz.scratchpad.view

import com.jtschwartz.scratchpad.Styles
import com.jtschwartz.scratchpad.config.Config
import com.jtschwartz.scratchpad.config.Constants
import com.jtschwartz.scratchpad.model.SavedContent
import com.jtschwartz.scratchpad.utils.ifEnter
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.scene.control.TextArea
import javafx.stage.Screen
import tornadofx.*
import java.io.*

class MainView: View(Config.TITLE) {
	private val search = SimpleStringProperty()
	private val replace = SimpleStringProperty()
	private val content = SimpleStringProperty()
	
	private val isRegexEnabled = SimpleBooleanProperty(false)
	private val isCaseSensitivityEnabled = SimpleBooleanProperty(false)
	private val isSelectionControlEnabled = SimpleBooleanProperty(false)
	
	private val file = File(Config.FILE_PATH)
	
	private lateinit var textArea: TextArea
	
	override val root =
		vbox {
			addClass(Styles.root)
			form {
				hbox {
					vbox {
						fieldset {
							field(Constants.SEARCH) {
								textfield(search) {
									onKeyReleased = EventHandler { key ->
										key.ifEnter {
											print("Hit enter")
										}
									}
								}
							}
							field(Constants.REPLACE) {
								textfield(replace) {
									onKeyReleased = EventHandler { key ->
										key.ifEnter {
											content.value = findAndReplace()
										}
									}
								}
							}
						}
					}.also {
						it.prefWidthProperty().bind(this.widthProperty().multiply(0.75))
					}
					vbox {
						fieldset {
							checkbox("Use Regex", isRegexEnabled).apply { paddingBottom = 5 }
							checkbox("Case Sensitive", isCaseSensitivityEnabled) {
								disableProperty().bind(isRegexEnabled)
							}.apply { paddingBottom = 5 }
							checkbox("Selection Control", isSelectionControlEnabled)
						}
					}.apply {
						paddingTop = 4
						paddingLeft = 10
					}
				}
			}
			textarea(content) {
				textArea = this
			}.also {
				it.prefWidthProperty().bind(this.widthProperty())
				it.prefHeightProperty().bind(this.heightProperty())
			}
		}
	
	init {
		with(root) {
			val screenBounds = Screen.getPrimary().bounds
			prefHeight = screenBounds.height * Config.WINDOW_HEIGHT_RATIO
			prefWidth = screenBounds.width * Config.WINDOW_WIDTH_RATIO
		}
		
		if (file.exists()) {
			ObjectInputStream(FileInputStream(Config.FILE_PATH)).use {
				val obj = it.readObject()
				if (obj is SavedContent) {
					search.value = obj.search
					replace.value = obj.replace
					content.value = obj.content
				}
			}
		}
	}
	
	private fun findAndReplace() = if (isSelectionControlEnabled.value) {
		val bounds = textArea.selection
		content.value.replaceRange(bounds.start, bounds.end, handleRegexReplacement(textArea.selectedText))
	} else {
		handleRegexReplacement(content.value)
	}
	
	private fun handleRegexReplacement(original: String) = if (isRegexEnabled.value) {
		original.replace(search.value.toRegex(), replace.value)
	} else {
		original.replace(search.value, replace.value, !isCaseSensitivityEnabled.value)
	}
	
	override fun onDock() {
		currentWindow?.setOnCloseRequest {
			ObjectOutputStream(FileOutputStream(Config.FILE_PATH)).use {
				it.writeObject(SavedContent(search.value ?: "", replace.value ?: "", content.value ?: ""))
			}
		}
	}
}
