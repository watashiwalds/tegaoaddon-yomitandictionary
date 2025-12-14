package com.tegaoteam.addon.tegao.yomitandictionary.query

import android.content.Context
import com.google.gson.JsonObject
import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDAO
import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDatabase

class DatabaseQueryCentre private constructor() {
    companion object {
        val instance by lazy { DatabaseQueryCentre() }
    }

    private lateinit var _db: DictionaryDAO

    fun lookup(context: Context, type: Int, keyword: String?): String {
        if (!::_db.isInitialized) {
            _db = DictionaryDatabase.getInstance(context)._dictionaryDao
        }

        val resJson = JsonObject()
        resJson.addProperty("success", true)
        resJson.addProperty("result", "Addon return: $type $keyword ${_db.countSize().size}")
        val resString = resJson.toString()
        return resString
    }

}