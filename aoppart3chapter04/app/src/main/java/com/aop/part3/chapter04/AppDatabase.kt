package com.aop.part3.chapter04

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aop.part3.chapter04.dao.HistoryDao
import com.aop.part3.chapter04.dao.ReviewDao
import com.aop.part3.chapter04.model.History
import com.aop.part3.chapter04.model.Review

@Database(entities = [History::class, Review::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
    abstract fun reviewDao(): ReviewDao
}