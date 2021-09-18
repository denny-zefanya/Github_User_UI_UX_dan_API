package zefanya.denny.githubuseruiuxdanapi.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.databinding.ActivitySplashScreenBinding
import zefanya.denny.githubuseruiuxdanapi.ui.home.HomeActivity
import zefanya.denny.githubuseruiuxdanapi.util.ThemeHelper

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val themeHelper = ThemeHelper()
        themeHelper.checkTheme(this)
        setContentView(viewBinding.root)
        Glide.with(this)
            .load(R.drawable.github)
            .into(viewBinding.imageView)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 2000)
    }
}