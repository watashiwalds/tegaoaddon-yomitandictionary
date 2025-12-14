package com.tegaoteam.addon.tegao.yomitandictionary.query

import com.google.gson.JsonObject

class DatabaseQueryCentre private constructor() {
    companion object {
        val instance by lazy { DatabaseQueryCentre() }
    }

    fun lookup(type: Int, keyword: String?): String {
        val resJson = JsonObject()
        resJson.addProperty("success", true)
        resJson.addProperty("result", "Addon return: $type $keyword")
        val resString = resJson.toString()
        return resString
    }

}