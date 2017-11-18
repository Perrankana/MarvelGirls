package pandiandcode.marvelgirls.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pandiandcode.marvelgirls.BR
import pandiandcode.marvelgirls.viewmodel.BaseViewModel

/**
 * Created by Rocio Ortega on 18/11/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        binding.setVariable(BR.model, getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    abstract fun getLayout(): Int
}