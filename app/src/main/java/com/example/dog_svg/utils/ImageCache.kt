package com.example.dog_svg.utils

import android.util.LruCache
import com.example.dog_svg.datamodels.DogApiResponse

object ImageCache {
    private const val CACHE_SIZE = 20

    // Creating an LRU cache for DogApiResponse with a maximum size of 20
    val cache = LruCache<String, DogApiResponse>(CACHE_SIZE)

    // Function to add data to the cache
    fun addToCache(key: String, data: DogApiResponse) {
        cache.put(key, data)
    }

    // Function to retrieve data from the cache
    fun getFromCache(key: String): DogApiResponse? {
        return cache.get(key)
    }

    // Ensure cache persistence across app sessions
    // This can be achieved by saving and loading the cache state appropriately
    // You might use SharedPreferences or other storage mechanisms for this purpose
    // For simplicity, this example does not include persistence logic

    // Clear the cache when needed
    fun clearCache() {
        cache.evictAll()
    }
}