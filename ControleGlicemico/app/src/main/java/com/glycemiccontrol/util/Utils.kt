package com.glycemiccontrol.util

import android.content.Context
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {


        fun showSimpleDialog(
            context: Context,
            title: Int,
            resString: Int,
            callback: MaterialDialog.SingleButtonCallback
        ) {
            showSimpleDialog(context, context.getString(title), context.getString(resString), callback)
        }

        fun showSimpleDialog(
            context: Context,
            title: String,
            text: String,
            callback: MaterialDialog.SingleButtonCallback
        ) {
            try {
                AnimationsUtil.shakeError(
                    MaterialDialog.Builder(context)
                        .title(title)
                        .content(text)
                        .cancelable(false)
                        .positiveText("Ok")
                        .onPositive(callback)
                        .show().getView(), AnimationsUtil.DURATION_DIALOG
                )

            } catch (ex: Exception) {
                Log.e("asd", "ads")
            }

        }

        fun dateToString(date: String, toPattern: String): String {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
                return dateToString(sdf.parse(date), toPattern)
            } catch (e: Exception) {
                return ""
            }
        }

        fun dateToString(date: Date, pattern: String): String {
            try {
                val simpleDateFormat = SimpleDateFormat(pattern, Locale("pt", "BR"))
                return simpleDateFormat.format(date)
            } catch (e: Exception) {
                return ""
            }
        }
    }
}
