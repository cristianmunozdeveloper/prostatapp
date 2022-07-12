package com.cristiansofthouse.testhistory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cristiansofthouse.testhistory.dao.TestHistoryDao
import com.cristiansofthouse.testhistory.models.TestHistory

@Database(
    entities = [TestHistory::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun testHistoryDao(): TestHistoryDao
}
