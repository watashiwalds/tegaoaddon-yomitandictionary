package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Words",
    indices = [
        Index(name = "idx_words_read", value = ["primary_reading"]),
        Index(name = "idx_words_write", value = ["primary_writing"])
    ]
)
data class Words(
    @PrimaryKey @ColumnInfo(name = "word_id") val wordId: Long,
    @ColumnInfo(name = "primary_reading") val readings: String?,
    @ColumnInfo(name = "primary_writing") val writings: String?,
    @ColumnInfo(name = "frequency_rank") val frequency: Long?,
    @ColumnInfo(name = "source_id") val sourceId: Long?
)