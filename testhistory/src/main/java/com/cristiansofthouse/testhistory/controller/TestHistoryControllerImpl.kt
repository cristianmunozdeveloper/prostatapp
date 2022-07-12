package com.cristiansofthouse.testhistory.controller

import com.cristiansofthouse.testhistory.dao.TestHistoryDao
import com.cristiansofthouse.testhistory.models.TestHistory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TestHistoryControllerImpl @Inject constructor(
    private val dao: TestHistoryDao
) : TestHistoryController {

    override fun getTestHistory(): Single<List<TestHistory>> {
        return dao.getTestHistory()
    }

    override fun insertTestHistory(testHistory: TestHistory): Completable {
        return dao.insertNewTestRegistry(testHistory)
    }
}
