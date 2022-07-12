package com.cristiansofthouse.testhistory.controller

import com.cristiansofthouse.testhistory.models.TestHistory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TestHistoryController {

    fun getTestHistory(): Single<List<TestHistory>>
    fun insertTestHistory(testHistory: TestHistory): Completable
}
