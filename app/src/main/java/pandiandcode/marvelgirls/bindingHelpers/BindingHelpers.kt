package pandiandcode.marvelgirls.bindingHelpers

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import pandiandcode.marvelgirls.R
import pandiandcode.marvelgirls.viewmodel.comics.ComicItemViewModel
import pandiandcode.marvelgirls.widget.ComicListAdapter

/**
 * Created by Rocio Ortega on 18/11/2017.
 */

@BindingAdapter("comicsList")
fun bindComicAdapter(recyclerView: RecyclerView, comicList: List<ComicItemViewModel>) {
    recyclerView.adapter?.let {
        (it as ComicListAdapter).mComicList = comicList as ObservableArrayList<ComicItemViewModel>
        it.notifyDataSetChanged()
    }

    when {
        recyclerView.adapter == null -> recyclerView.adapter = ComicListAdapter(comicList as ObservableArrayList<ComicItemViewModel>)
    }
}

@BindingAdapter(value = *arrayOf("android:src"), requireAll = false)
fun setImageUrl(view: ImageView, url: String) {
    if (!TextUtils.isEmpty(url)) {
        Picasso.with(view.context)
                .load(url)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_place_holder)
                .into(view)
    } else {
        view.setImageResource(R.drawable.ic_place_holder)
    }
}

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int = when {
    visible -> View.VISIBLE
    else -> View.GONE
}

