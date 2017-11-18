package pandiandcode.marvelgirls.widget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
class BindingHolder @JvmOverloads constructor(rowView: View, canBind: Boolean = true) : RecyclerView.ViewHolder(rowView) {

    var binding: ViewDataBinding? = null
        private set

    init {
        if (canBind) {
            binding = DataBindingUtil.bind(rowView)
        }
    }

}