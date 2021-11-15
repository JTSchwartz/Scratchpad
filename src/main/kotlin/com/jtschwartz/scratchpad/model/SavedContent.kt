package com.jtschwartz.scratchpad.model

import java.io.Serializable

data class SavedContent(val search: String, val replace: String, val content: String): Serializable
