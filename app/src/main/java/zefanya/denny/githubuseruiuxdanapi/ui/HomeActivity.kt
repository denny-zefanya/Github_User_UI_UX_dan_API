package zefanya.denny.githubuseruiuxdanapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zefanya.denny.githubuseruiuxdanapi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}