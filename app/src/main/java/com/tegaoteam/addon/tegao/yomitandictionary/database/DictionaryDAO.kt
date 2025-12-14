package com.tegaoteam.addon.tegao.yomitandictionary.database

import androidx.room.Dao
import androidx.room.Query
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Kanjis

@Dao
interface DictionaryDAO {
    @Query("""
        select * from "Kanji" where "character" in (:keyword)
    """)
    fun searchKanjiByChar(keyword: List<String>): List<Kanjis>

    @Query("""
        select * from "Kanji" where "kun_reading" like (:keyword)
    """)
    fun searchKanjiByKun(keyword: String): List<Kanjis>

    @Query("""
        select * from "Kanji" where "on_reading" in (:keyword)
    """)
    fun searchKanjiByOn(keyword: String): List<Kanjis>
}