package com.tegaoteam.addon.tegao.yomitandictionary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tegaoteam.addon.tegao.yomitandictionary.AddonApplication
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Kanjis
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Senses
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Sources
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Tags
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.WordTags
import com.tegaoteam.addon.tegao.yomitandictionary.database.tables.Words

@Database(
    version = 1,
    entities = [
        Kanjis::class,
        Senses::class,
        Sources::class,
        Tags::class,
        Words::class,
        WordTags::class
    ],
    exportSchema = false
)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract val _dictionaryDao: DictionaryDAO

    companion object {
        const val DATABASE_NAME = "yomitan_dictionary.db"

        @Volatile
        private var _instance: DictionaryDatabase? = null

        fun getInstance(context: Context = AddonApplication.instance): DictionaryDatabase {
            synchronized(this) {
                var instance = _instance
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, DictionaryDatabase::class.java, DATABASE_NAME)
                        .createFromAsset("dictionary.db")
                        .fallbackToDestructiveMigration(true)
                        .build()
                    _instance = instance
                }
                return _instance!!
            }
        }
    }

}