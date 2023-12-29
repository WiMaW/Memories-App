package wioleta.wrobel.memoriesapp.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import wioleta.wrobel.memoriesapp.model.Memory

object JsonConverter {
    private val moshi = Moshi.Builder().build()

    fun memoryToJason(memory: Memory): String {
        val type = Memory::class.java
        return moshi.adapter<Memory>(type).toJson(memory)
    }

    fun memoryFromJason(json: String): Memory? {
        val type = Memory::class.java
        return moshi.adapter<Memory>(type).fromJson(json)
    }

    fun memoryListToJason(memoryList: List<Memory>): String {
        val type = Types.newParameterizedType(List::class.java, Memory::class.java)
        return moshi.adapter<List<Memory>>(type).toJson(memoryList)
    }

    fun memoryListFromJson(json: String): List<Memory> {
        val type = Types.newParameterizedType(List::class.java, Memory::class.java)
        return moshi.adapter<List<Memory>>(type).fromJson(json) ?: emptyList()
    }

}