package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sources")
data class Sources(
    @PrimaryKey @ColumnInfo(name = "source_id") val sourceId: Long,
    @ColumnInfo(name = "source_name") val name: String?,
    @ColumnInfo(name = "update_date") val lastUpdate: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "version") val version: String?
)
