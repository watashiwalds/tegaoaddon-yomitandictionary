package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Kanji",
    indices = [
        Index(name = "idx_kanji_char", value = ["character"])
    ])
data class Kanjis(
    @PrimaryKey @ColumnInfo(name = "kanji_id") val kanjiId: Long,
    @ColumnInfo(name = "character") val character: String?,
    @ColumnInfo(name = "on_reading") val onyomi: String?,
    @ColumnInfo(name = "kun_reading") val kunyomi: String?,
    @ColumnInfo(name = "meaning") val meaning: String?
)
