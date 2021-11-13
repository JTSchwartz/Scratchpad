package com.jtschwartz.scratchpad.view

import com.jtschwartz.scratchpad.Styles
import com.jtschwartz.scratchpad.config.Config
import com.jtschwartz.scratchpad.config.Constants
import com.jtschwartz.scratchpad.utils.ifEnter
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.stage.Screen
import tornadofx.*

class MainView: View(Config.TITLE) {
	private val search = SimpleStringProperty()
	private val replace = SimpleStringProperty()
	
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
											print("Hit enter")
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
							checkbox("Use Regex").apply { paddingBottom = 5 }
							checkbox("Case Sensitive").apply { paddingBottom = 5 }
							checkbox("Selection Control")
						}.also {
							it.paddingTop = 4
							it.paddingLeft = 10
						}
					}
				}
			}
			textarea {
				addClass(Styles.textArea)
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
	}
}
