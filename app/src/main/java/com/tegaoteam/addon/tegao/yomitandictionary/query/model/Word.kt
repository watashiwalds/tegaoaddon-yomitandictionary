package com.tegaoteam.addon.tegao.yomitandictionary.query.model

data class Word(
    val id: String,
    val reading: String,
    val furigana: List<String>? = null,
    var tags: List<Tag>? = null,
    var additionalInfo: List<AdditionalInfo>? = null,
    val definitions: List<Definition>
) {
    data class Definition(
        var tags: List<Tag>? = null,
        val meaning: String,
        var expandInfos: List<ExpandInfo>? = null
    ) {

        data class Tag(
            val termKey: String,
            val label: String,
            val description: String
        )

        data class ExpandInfo(
            val termKey: String,
            val content: String
        )
    }

    data class Tag(
        val termKey: String,
        val label: String
    )

    data class AdditionalInfo(
        val termKey: String,
        val content: String
    )
}
