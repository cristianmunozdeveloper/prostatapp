package com.cristiansofthouse.testhistory.di

import android.content.Context
import androidx.room.Room
import com.cristiansofthouse.testhistory.dao.TestHistoryDao
import com.cristiansofthouse.testhistory.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "testhistorydb")
            .build()
    }

    @Provides
    @Singleton
    fun provideTestHistoryDao(database: Database): TestHistoryDao {
        return database.testHistoryDao()
    }
}
