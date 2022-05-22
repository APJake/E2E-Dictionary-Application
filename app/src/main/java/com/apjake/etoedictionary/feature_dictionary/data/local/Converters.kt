package com.apjake.etoedictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.apjake.etoedictionary.feature_dictionary.data.util.JsonParser
import com.apjake.etoedictionary.feature_dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> = jsonParser.fromJson<ArrayList<Meaning>>(
        json = json,
        type = object : TypeToken<ArrayList<Meaning>>(){}.type
    ).orEmpty()

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String = jsonParser.toJson(
        obj = meanings,
        type = object : TypeToken<ArrayList<Meaning>>(){}.type
    )?: "[]"

    @TypeConverter
    fun fromStringListJson(json: String): List<String> = jsonParser.fromJson<ArrayList<String>>(
        json = json,
        type = object : TypeToken<ArrayList<String>>(){}.type
    ).orEmpty()

    @TypeConverter
    fun toStringListJson(list: List<String>): String = jsonParser.toJson(
        obj = list,
        type = object : TypeToken<ArrayList<String>>(){}.type
    )?: "[]"

}