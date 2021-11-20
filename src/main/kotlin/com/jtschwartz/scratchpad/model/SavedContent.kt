package com.jtschwartz.scratchpad.model

import java.io.Serializable

data class SavedContent(
	val search: String,
	val replace: String,
	val content: String,
	val isRegexEnabled: Boolean,
	val isCaseSensitivityEnabled: Boolean,
	val isSelectionControlEnabled: Boolean
                       ): Serializable
