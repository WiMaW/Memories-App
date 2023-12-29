package wioleta.wrobel.memoriesapp.util

import android.content.Context
import wioleta.wrobel.memoriesapp.model.Memory

object StorageOperation {
    fun writeMemoryList(context: Context, memoryList: List<Memory>) {

        val json = JsonConverter.memoryListToJason(memoryList)

        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit()
        sharedPrefs.putString(MEMORY_LIST_KEY, json)
        sharedPrefs.apply()
    }

    fun readMemoryList(context: Context): List<Memory> {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPrefs.getString(MEMORY_LIST_KEY, null)
        return if (json != null) JsonConverter.memoryListFromJson(json) else emptyList()
    }

    fun readMemory(context: Context): Memory? {
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPrefs.getString(MEMORY_KEY, null)
        return if ( json != null) JsonConverter.memoryFromJason(json) else null
    }

    fun writeMemory(context: Context, memory: Memory) {
        val json = JsonConverter.memoryToJason(memory)

        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit()
        sharedPrefs.putString(MEMORY_KEY, json)
        sharedPrefs.apply()
    }


    private const val SHARED_PREFS_NAME = "MEMORY_SHARED_PREFS"
    private const val MEMORY_LIST_KEY = "MEMORY_LIST_KEY"
    private const val MEMORY_KEY = "MEMORY_KEY"
}