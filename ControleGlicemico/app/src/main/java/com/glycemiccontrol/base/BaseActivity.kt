package com.glycemiccontrol.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import cn.pedant.SweetAlert.SweetAlertDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.glycemiccontrol.R
import com.glycemiccontrol.util.AnimationsUtil

open class BaseActivity : AppCompatActivity() {

    private var dialogProgress: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    private fun initDialogProgress() {
        dialogProgress = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        dialogProgress!!.progressHelper.barColor = ContextCompat.getColor(this, R.color.colorAccent)
        dialogProgress!!.titleText = "Carregando"
        dialogProgress!!.setCancelable(false)
    }

    fun openActivity(openActivity: Class<*>) {
        val intent = Intent()
        intent.setClass(this, openActivity)
        startActivity(intent)
    }

    //
    fun openActivityNewTask(openActivity: Class<*>) {
        val intent = Intent()
        intent.setClass(this, openActivity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun openActivity(openActivity: Class<*>, bundle: Bundle) {
        val intent = Intent()
        intent.setClass(this, openActivity)
//        intent.putExtra(REGISTER, bundle)
        startActivity(intent)
    }

    fun finish(resultCode: Int) {
        setResult(resultCode)
        finish()
    }

    fun finish(resultCode: Int, bundle: Bundle) {
        intent.putExtras(bundle)
        setResult(resultCode, intent)
        finish()
    }

    fun showDialogProgress() {
        if (dialogProgress == null) initDialogProgress()
        dialogProgress!!.titleText = "Carregando"
        if (!dialogProgress!!.isShowing) dialogProgress!!.show()
    }

    fun showDialogProgress(text: String) {
        if (dialogProgress == null) initDialogProgress()
        dialogProgress!!.titleText = text
        if (!dialogProgress!!.isShowing) dialogProgress!!.show()
    }

    fun hideDialogProgress() {
        if (dialogProgress != null && dialogProgress!!.isShowing) dialogProgress!!.dismiss()
    }

    fun showSimpleDialog(title: String, text: String, callback: MaterialDialog.SingleButtonCallback) {
        try {
            AnimationsUtil.shakeError(
                MaterialDialog.Builder(this)
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