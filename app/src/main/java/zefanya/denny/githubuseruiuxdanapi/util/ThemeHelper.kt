package zefanya.denny.githubuseruiuxdanapi.util

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import zefanya.denny.githubuseruiuxdanapi.R

class ThemeHelper{

    fun chooseThemeDialog(context: Context) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.choose_theme))
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = MyPreferences(context).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(context).darkMode = 0
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(context).darkMode = 1
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(context).darkMode = 2
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }


    fun checkTheme(context: Context) {
        when (MyPreferences(context).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}