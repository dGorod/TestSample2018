package ua.dgorod.sample.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
fun Context.longToast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()