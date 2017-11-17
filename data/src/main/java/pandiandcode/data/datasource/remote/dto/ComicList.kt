package pandiandcode.data.datasource.remote.dto

/**
 * Created by Rocio Ortega on 17/11/2017.
 */
data class ComicList(val code: Int,
                     val status: String,
                     val data: Data<ComicItem>)

data class Data<T>(val offset: Int,
                   val limit: Int,
                   val total: Int,
                   val count: Int,
                   val results: ArrayList<T>)

data class ComicItem(val id: Int,
                     val digitalId: Int,
                     val title: String,
                     val issueNumber: Int,
                     val variantDescription: String,
                     val description: String,
                     val modified: String,
                     val isbn: String,
                     val upc: String,
                     val diamondCode: String,
                     val ean: String,
                     val issn: String,
                     val format: String,
                     val pageCount: Int,
                     val resourceURI: String,
                     val textObjects: ArrayList<TextObject>,
                     val urls: ArrayList<Url>,
                     val series: Issue,
                     val collectionIssues: ArrayList<Issue>,
                     val dates: ArrayList<Date>,
                     val prices: ArrayList<Price>,
                     val thumbnail: Image,
                     val images: ArrayList<Image>)

data class TextObject(val type: String,
                      val language: String,
                      val text: String)

data class Url(val type: String,
               val url: String)

data class Issue(val resourceUrl: String,
                 val name: String)

data class Date(val type: String,
                val date: String)

data class Price(val type: String,
                 val price: String)

data class Image(val path: String,
                 val extension: String)

