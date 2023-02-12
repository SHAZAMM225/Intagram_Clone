package com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram_clone.R
import kotlinx.android.synthetic.main.fragment_profile.*

class AccountSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)


        edit_account
    }
}