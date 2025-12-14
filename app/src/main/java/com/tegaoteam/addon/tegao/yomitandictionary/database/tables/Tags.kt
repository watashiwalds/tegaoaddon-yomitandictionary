package com.tegaoteam.addon.tegao.yomitandictionary.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tags")
data class Tags(
    @PrimaryKey @ColumnInfo(name = "tag_id") val tagId: Long,
    @ColumnInfo(name = "tag_name") val name: String?,
    @ColumnInfo(name = "description") val description: String?
)
