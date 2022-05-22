package com.apjake.etoedictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apjake.etoedictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfoList(infoList: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfoList(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%'|| :word || '%'")
    suspend fun getWordInfoList(word: String): List<WordInfoEntity>


}