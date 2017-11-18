package pandiandcode.data.datasource.remote.mapper

import pandiandcode.data.datasource.remote.dto.ComicItem
import pandiandcode.data.datasource.remote.dto.ComicList
import pandiandcode.databoundary.ComicData
import pandiandcode.databoundary.ComicListData

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
fun toComicListData(comicList: ComicList): ComicListData {
    val comicListData: ArrayList<ComicData> = ArrayList()
    comicList.data.results.forEach { comicListData.add(toComicData(it)) }
    return ComicListData(comicListData)
}

fun toComicData(comicItem: ComicItem): ComicData
        = ComicData(comicItem.id, comicItem.title, comicItem.description, "${comicItem.thumbnail.path}.${comicItem
        .thumbnail.extension}", getImages(comicItem))

fun getImages(comicItem: ComicItem): ArrayList<String> {
    val images: ArrayList<String> = ArrayList()
    comicItem.images.forEach { image -> images.add("${image.path}.${image.extension}") }
    return images
}




