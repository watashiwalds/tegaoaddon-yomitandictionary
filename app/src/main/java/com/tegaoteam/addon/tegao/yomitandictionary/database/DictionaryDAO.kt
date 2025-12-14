package com.tegaoteam.addon.tegao.yomitandictionary.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DictionaryDAO {
    @Query("""
        select "word_id" from "Words"
    """)
    fun countSize(): List<Long>
}