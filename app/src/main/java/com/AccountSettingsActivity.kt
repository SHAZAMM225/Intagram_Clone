package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram_clone.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_account_settings.*

class AccountSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)



log_out_acount_btn.setOnClickListener {

    FirebaseAuth.getInstance().signOut()
    val i = Intent(this@AccountSettingsActivity, SignInActivity::class.java)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(i)
    finish()

}


    }
}