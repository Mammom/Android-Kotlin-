package com.aop.part3.chapter04.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aop.part3.chapter04.model.Review


@Dao
interface ReviewDao {

    @Query("SELECT * FROM review WHERE id == :id")
    fun getOnReview(id:Int): Review?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review)

}