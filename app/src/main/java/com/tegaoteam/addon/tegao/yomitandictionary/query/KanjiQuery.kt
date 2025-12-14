package com.tegaoteam.addon.tegao.yomitandictionary.query

import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDAO
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Kanjis

class KanjiQuery(private val _db: DictionaryDAO) {
    fun searchKanjis(keyword: String): List<Kanjis> {
        val strippedKeyword = yomiChecker(keyword)
        when (keywordType) {
            TYPE_KANJI -> {
                val kanjiList = strippedKeyword.toList().map { char -> char.toString() }
                return _db.searchKanjiByChar(kanjiList)
            }
            TYPE_KUN -> {
                return _db.searchKanjiByKun("%$keyword%")
            }
            TYPE_ON -> {
                return _db.searchKanjiByOn("%$keyword%")
            }
            else -> return listOf()
        }
    }

    private var keywordType: Int = 0
    private fun yomiChecker(keyword: String): String {
        var kun = StringBuilder()
        var on = StringBuilder()
        val kan = StringBuilder()
        keyword.toList().forEach { char ->
            if (ConstValue.HIRAGANAS.contains(char)) kun.append(char)
            else if (ConstValue.KATAKANAS.contains(char)) on.append(char)
            else kan.append(char)
        }
        var s = kan.toString()
        if (s.isNotBlank()) {
            keywordType = TYPE_KANJI
            return s
        }
        s = kun.toString()
        if (s.isBlank()) {
            keywordType = TYPE_ON
            return on.toString()
        } else {
            keywordType = TYPE_KUN
            return kun.toString()
        }
    }

    companion object {
        const val TYPE_KANJI = 0
        const val TYPE_KUN = 1
        const val TYPE_ON = 2
    }
}