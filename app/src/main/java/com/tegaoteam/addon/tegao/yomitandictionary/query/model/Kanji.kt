package com.tegaoteam.addon.tegao.yomitandictionary.query.model

import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Kanjis

data class Kanji(
    val id: String,
    val character: String,
    var kunyomi: List<String>? = null,
    var onyomi: List<String>? = null,
    var composites: List<Composite>? = null,
    val meaning: String,
    var tags: List<Tag>? = null,
    var additionalInfo: List<AdditionalInfo>? = null,
) {
    data class Composite(
        val character: String,
        val hanji: String?
    )

    data class Tag(
        val termKey: String,
        val label: String
    )

    data class AdditionalInfo(
        val termKey: String,
        val content: String
    )

    companion object {
        fun toReturnKanji(qKanji: Kanjis) = Kanji(
            id = qKanji.kanjiId.toString(),
            character = qKanji.character?: "",
            kunyomi = qKanji.kunyomi?.split(" "),
            onyomi = qKanji.onyomi?.split(" "),
            meaning = qKanji.meaning?: ""
        )
    }
}