package com.apjake.etoedictionary.feature_dictionary.data.repository

import com.apjake.etoedictionary.core.util.Resource
import com.apjake.etoedictionary.feature_dictionary.data.local.WordInfoDao
import com.apjake.etoedictionary.feature_dictionary.data.remote.DictionaryApi
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import com.apjake.etoedictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        return flow {
            emit(Resource.Loading())

            val wordInfoList= dao.getWordInfoList(word).map { it.toWordInfo() }
            emit(Resource.Loading(data = wordInfoList))

            try{
                val remoteWordInfoList = api.getWordInfo(word)
                dao.deleteWordInfoList(remoteWordInfoList.map { it.word.orEmpty() })
                dao.insertWordInfoList(remoteWordInfoList.map { it.toWordInfoEntity() })
            }catch (e: HttpException){
                emit(Resource.Error(
                    message = "Something went wrong!",
                    data = wordInfoList
                ))
            }catch (e: IOException){
                emit(Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = wordInfoList
                ))
            }

            val newWordInfoList = dao.getWordInfoList(word).map { it.toWordInfo() }
            emit(Resource.Success(newWordInfoList))

        }
    }
}