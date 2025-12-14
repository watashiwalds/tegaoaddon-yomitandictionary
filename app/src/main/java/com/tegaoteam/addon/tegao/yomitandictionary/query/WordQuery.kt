package com.tegaoteam.addon.tegao.yomitandictionary.query

import com.tegaoteam.addon.tegao.yomitandictionary.database.DictionaryDAO
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Words
import com.tegaoteam.addon.tegao.yomitandictionary.query.model.Word

class WordQuery(private val _db: DictionaryDAO) {
    fun searchWords(keyword: String): List<Word> {
        val matchLongest = searchWordsMatchLongestKeyword(keyword)
        val longestKeyword = matchLongest.first
        var resWords = matchLongest.second
        if (longestKeyword.isEmpty()) return emptyList()
        
        val resWord = mutableListOf<Word>()
        resWords.forEach { ws -> 
            val tgs = _db.getTagsOfWordId(ws.wordId)
            val sns = _db.getSensesOfWordId(ws.wordId)

            var tags = tgs.map { Word.Tag(
                termKey = it.name?: "",
                label = it.name?: "",
                description = it.description
            ) }.toMutableList().apply {
                add(Word.Tag(
                    termKey = "frequency",
                    label = ws.frequency?.toString()?: ""
                ))
            }
            val defs = sns.map { Word.Definition(
                meaning = it.definition?: ""
            ) }

            val word = Word(
                id = ws.wordId.toString(),
                reading = ws.writings?: "",
                furigana = if (ws.readings != null) listOf(ws.readings) else emptyList(),
                tags = tags,
                definitions = defs
            )
            resWord.add(word)
        }
        return resWord
    }

    private fun searchWordsMatchLongestKeyword(keyword: String): Pair<String, List<Words>> {
        val keywordSb = StringBuilder().append(keyword)
        while (keywordSb.isNotEmpty()) {
            val res = _db.searchWordEntry(keywordSb.toString())
            if (res.isNotEmpty()) return Pair(keywordSb.toString(), res)
            keywordSb.setLength(keyword.length - 1)
        }
        return Pair("", emptyList())
    }
}