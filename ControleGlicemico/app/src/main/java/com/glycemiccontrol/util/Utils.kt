package com.glycemiccontrol.util

import android.content.Context
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog

class Utils{

    fun showSimpleDialog(context: Context, title: Int, resString: Int, callback: MaterialDialog.SingleButtonCallback) {
        showSimpleDialog(context, context.getString(title), context.getString(resString), callback)
    }

    fun showSimpleDialog(context: Context, title: String, text: String, callback: MaterialDialog.SingleButtonCallback) {
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
}
