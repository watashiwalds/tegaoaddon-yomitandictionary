package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Word_Tags",
    indices = [
        Index(name = "idx_wt_tid", value = ["tag_id"]),
        Index(name = "idx_wt_wid", value = ["word_id"])
    ]
)
data class WordTags(
    @PrimaryKey @ColumnInfo(name = "word_tag_id") val wtId: Long,
    @ColumnInfo(name = "word_id") val wordId: Long,
    @ColumnInfo(name = "tag_id") val tagId: Long
)
