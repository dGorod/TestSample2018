package ua.dgorod.sample.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.systemService

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) : View {
    return context.systemService<LayoutInflater>().inflate(layout, this, attachToRoot)
}