package com.example.ramk.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ramk.core.local.entity.CharacterEntity

@Dao
interface CharacterDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertCharacter(character: CharacterEntity)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAll(characters: List<CharacterEntity>)

     @Delete
     suspend fun deleteCharacter(character: CharacterEntity)

     @Query(value = "delete from favourite_character where id = :characterId")
     suspend fun deleteByID(characterId: Int)

     @Query(value = "select * from favourite_character where id= :characterId")
     suspend fun getCharacterByID(characterId: Int)

     @Query(value = "select * from favourite_character order by name desc")
     suspend fun getAllFavouriteCharacters()

     @Query(value = "select count(*) from favourite_character")
     suspend fun  getCountFavouriteCharacter()
}