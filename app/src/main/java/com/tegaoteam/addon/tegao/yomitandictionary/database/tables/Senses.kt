package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Senses",
    indices = [
        Index(name = "idx_senses_wid", value = ["word_id"])
    ]
)
data class Senses(
    @PrimaryKey @ColumnInfo(name = "sense_id") val senseId: Long,
    @ColumnInfo(name = "word_id") val wordId: Long,
    @ColumnInfo(name = "definition_en") val definition: String?,
    @ColumnInfo(name = "sense_number") val index: Long?
)