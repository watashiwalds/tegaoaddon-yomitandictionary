package com.tegaoteam.addon.tegao.yomitandictionary.database

import androidx.room.Dao
import androidx.room.Query
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Kanjis
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Senses
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Tags
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Words

@Dao
interface DictionaryDAO {
    @Query("""
        select * from Kanji where character in (:keyword)
    """)
    fun searchKanjiByChar(keyword: List<String>): List<Kanjis>

    @Query("""
        select * from Kanji where kun_reading like :keyword
    """)
    fun searchKanjiByKun(keyword: String): List<Kanjis>

    @Query("""
        select * from Kanji where on_reading like :keyword
    """)
    fun searchKanjiByOn(keyword: String): List<Kanjis>

    @Query("""
        select * from Words where primary_reading = :keyword or primary_writing = :keyword order by frequency_rank DESC
    """)
    fun searchWordEntry(keyword: String): List<Words>

    @Query("""
        select * from Tags where tag_id in ( select tag_id from Word_Tags where word_id = :wordId )
    """)
    fun getTagsOfWordId(wordId: Long): List<Tags>

    @Query("""
        select * from Senses where word_id = :wordId
    """)
    fun getSensesOfWordId(wordId: Long): List<Senses>
}