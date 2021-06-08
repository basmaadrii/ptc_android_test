package android.ptc.com.ptcflixing.ui.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.ptc.com.ptcflixing.BR

class ToolbarData : BaseObservable() {

    fun handleHome(title: String = "", showBack: Boolean = false) {
        showTitle = true
        this.showBack = showBack
        this.title = title

    }


    fun handleNormal(title: String = "", showNav: Boolean = true) {
        showTitle = true
        showBack = true
        this.title = title
        this.showNav = showNav
    }

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }


    @get:Bindable
    var showTitle: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showTitle)
        }

    @get:Bindable
    var showLogo: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLogo)
        }

    @get:Bindable
    var showBack: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showBack)
        }


    @get:Bindable
    var showBar: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showBar)
        }

    @get:Bindable
    var showNav: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showNav)
        }

}