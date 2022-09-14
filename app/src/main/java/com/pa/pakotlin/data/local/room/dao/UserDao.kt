package com.pa.pakotlin.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.pa.pakotlin.data.local.room.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("Select * FROM user")
    suspend fun getAllUser(): List<UserEntity>

    @Query("Select *  FROM user WHERE user = :user")
    suspend fun getUser(user: String): UserEntity?

    @Query("Select name  FROM user WHERE user = :user")
    suspend fun getName(user: String): String

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}
