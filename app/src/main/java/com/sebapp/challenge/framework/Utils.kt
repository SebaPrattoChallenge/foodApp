package com.sebapp.challenge.framework

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.sebapp.challenge.R
import com.sebapp.challenge.framework.common.Constants.Companion.DATE_APP_FORMAT
import com.sebapp.challenge.framework.common.Constants.Companion.ISO_8601_DATE_FORMAT
import com.sebapp.challenge.framework.common.Constants.Companion.SNACK_BAR_DURATION
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.showSnackError(message: String) {

    Snackbar.make(requireView(), message, SNACK_BAR_DURATION)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red))
        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .show()
}

fun Fragment.toast(text: String, longMessage: Boolean = true) {
    if (longMessage) {
        Toast.makeText(this.requireContext(), text, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}

fun String.formatDate(): String {
    val parser = SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.US)
    val formatter = SimpleDateFormat(DATE_APP_FORMAT, Locale.US)
    return formatter.format(parser.parse(this) ?: Date())
}

fun Double.roundOffDecimal(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return "$" + df.format(this) + " USD"
}

fun View.hideKeyboard() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

fun FragmentActivity.whenBackPressed(viewLifecycleOwner: LifecycleOwner) {
    this.onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                this@whenBackPressed.finish()
            }
        })
}

