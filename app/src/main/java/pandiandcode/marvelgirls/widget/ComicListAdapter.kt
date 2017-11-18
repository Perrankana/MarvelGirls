package pandiandcode.marvelgirls.widget

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.R
import pandiandcode.marvelgirls.viewmodel.comics.ComicItemViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class ComicListAdapter(comicList: ArrayList<ComicItemViewModel>) : RecyclerView.Adapter<BindingHolder>() {
    private val ITEM_TYPE = 0
    private val LOADING_TYPE = 1

    var mComicList = comicList

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingHolder =
            when (viewType) {
                ITEM_TYPE -> BindingHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.layout_comic_list_item, parent, false))
                LOADING_TYPE -> BindingHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.layout_loading_item, parent, false), false)

                else -> {
                    throw UnsupportedOperationException()
                }
            }


    override fun onBindViewHolder(holder: BindingHolder?, position: Int) {
        when (holder?.itemViewType) {
            ITEM_TYPE -> {
                val comicItemViewModel = mComicList[position]

                holder.binding?.setVariable(BR.model, comicItemViewModel)

                holder.binding?.executePendingBindings()
            }
            LOADING_TYPE -> {
            }
        }
    }

    override fun getItemCount(): Int = mComicList.size
}