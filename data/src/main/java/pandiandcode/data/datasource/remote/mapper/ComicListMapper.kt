package pandiandcode.data.datasource.remote.mapper

import pandiandcode.data.datasource.remote.dto.ComicItem
import pandiandcode.data.datasource.remote.dto.ComicList
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
fun toComicListData(comicList: ComicList): ComicListData {
    val comicListData: ArrayList<ComicData> = ArrayList()
    comicList.data.results.forEach { comicItem -> comicListData.add(toComicData(comicItem)) }
    return ComicListData(comicListData)
}

fun toComicData(comicItem: ComicItem): ComicData
        = ComicData(comicItem.id, comicItem.title, comicItem.description, comicItem.thumbnail.path, getRandomImage
(comicItem))

fun getRandomImage(comicItem: ComicItem): String {
    return when {
        comicItem.images.isEmpty() -> comicItem.thumbnail.path
        comicItem.images.size == 1 -> comicItem.images[0].path
        else -> comicItem.images[getRandomNumber(comicItem.images.size - 1, 0)].path
    }
}

fun getRandomNumber(to: Int, from: Int): Int = Random().nextInt(to - from) + from


