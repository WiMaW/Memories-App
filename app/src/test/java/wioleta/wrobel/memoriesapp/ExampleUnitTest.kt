package wioleta.wrobel.memoriesapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //given
        var json = "[{\"memoryDate\":\"2023-12-21\",\"memoryTitle\":\"tak\",\"memoryDescription\":\"ghgh\",\"memoryImage\":\"content://media/picker/0/com.android.providers.media.photopicker/media/1000000019\",\"cardColorType\":\"BLUE\",\"fontColorType\":\"GREEN\",\"fontType\":\"BERKSHIRE\"},{\"memoryDate\":\"2023-12-23\",\"memoryTitle\":\"test\",\"memoryDescription\":\"zdjecie\",\"memoryImage\":\"content://media/picker/0/com.android.providers.media.photopicker/media/1000000019\",\"cardColorType\":\"PINK\",\"fontColorType\":\"GREEN\",\"fontType\":\"YESEVA\"},{\"memoryDate\":\"2023-12-23\",\"memoryTitle\":\"bmnowe\",\"memoryDescription\":\"chjnn\",\"memoryImage\":\"content://media/picker/0/com.android.providers.media.photopicker/media/1000000018\",\"cardColorType\":\"PINK\",\"fontColorType\":\"GREY\",\"fontType\":\"LOBSTER\"},{\"memoryDate\":\"2023-12-23\",\"memoryTitle\":\"nowe\",\"memoryDescription\":\"ghjj\",\"memoryImage\":\"content://media/picker/0/com.android.providers.media.photopicker/media/1000000019\",\"cardColorType\":\"GREEN\",\"fontColorType\":\"GREY\",\"fontType\":\"LOBSTER\"},{\"memoryDate\":\"2023-12-28\",\"memoryTitle\":\"gh\",\"memoryDescription\":\"gh\",\"memoryImage\":\"content://media/picker/0/com.android.providers.media.photopicker/media/1000000019\",\"cardColorType\":\"PINK\",\"fontColorType\":\"PINK\",\"fontType\":\"YESEVA\"}]"

        //
        var jsonConverter = wioleta.wrobel.memoriesapp.util.JsonConverter
        var result = jsonConverter.memoryListFromJson(json)

        assertEquals(5, result.count())
        assertTrue(result.first().memoryImage.isNotEmpty())
    }
}