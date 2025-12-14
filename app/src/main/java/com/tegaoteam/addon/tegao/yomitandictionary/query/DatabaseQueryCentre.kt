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
    private lateinit var _wordQuery: WordQuery

    private val gson = Gson()

    fun lookup(context: Context, type: Int, keyword: String?): String {
        if (!::_db.isInitialized) {
            _db = DictionaryDatabase.getInstance(context)._dictionaryDao
            _kanjiQuery = KanjiQuery(_db)
            _wordQuery = WordQuery(_db)
        }

        when (type) {
            //Word type = 0
            0 -> {
                val wordRes = _wordQuery.searchWords(keyword?: "")
                val json = gson.toJson(wordRes)
                return "$json"
            }
            //Kanji type = 1
            1 -> {
                val kanjiRes = _kanjiQuery.searchKanjis(keyword?: "")
                val json = gson.toJson(kanjiRes)
                return "$json"
            }
            else -> return ""
        }
    }
}