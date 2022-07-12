package com.cristiansofthouse.testhistory.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cristiansofthouse.testhistory.models.TestHistory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TestHistoryDao {

    @Query("select * from TestHistory order by date desc")
    fun getTestHistory(): Single<List<TestHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewTestRegistry(testHistory: TestHistory): Completable
}
