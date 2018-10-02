package ua.dgorod.sample.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}