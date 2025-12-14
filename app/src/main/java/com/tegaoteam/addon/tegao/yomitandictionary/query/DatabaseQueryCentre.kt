package com.tegaoteam.addon.tegao.yomitandictionary.query

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDAO
import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDatabase
import com.tegaoteam.addon.tegao.yomitandictionary.query.model.Kanji

class DatabaseQueryCentre private constructor() {
    companion object {
        val instance by lazy { DatabaseQueryCentre() }
    }

    private lateinit var _db: DictionaryDAO
    private lateinit var _kanjiQuery: KanjiQuery

    private val gson = Gson()

    fun lookup(context: Context, type: Int, keyword: String?): String {
        if (!::_db.isInitialized) {
            _db = DictionaryDatabase.getInstance(context)._dictionaryDao
            _kanjiQuery = KanjiQuery(_db)
        }

        //Kanji
        if (type == 1) {
            val kanjis = _kanjiQuery.searchKanjis(keyword?: "").map { Kanji.toReturnKanji(it) }
            val json = gson.toJson(kanjis)
            return "$json"
        }

        val resJson = JsonObject()
        resJson.addProperty("success", true)
        resJson.addProperty("result", "Addon return: $type $keyword")
        val resString = resJson.toString()
        return resString
    }
}