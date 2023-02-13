package com

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.instagram_clone.MainActivity
import com.example.instagram_clone.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        already_have.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))

        }

        login_btn.setOnClickListener {
     loginUser()
        }
    }

    private fun loginUser() {

        val email = email_logo.text.toString()
        val pass = pass_logo.text.toString()


             when{
                 TextUtils.isEmpty(email) ->message(email)
                 TextUtils.isEmpty(pass) ->message(pass)

                 else ->{
                     val progressDialog= ProgressDialog(this@SignInActivity)
                     progressDialog.setTitle("SignUp")
                     progressDialog.setMessage("Please Wait ,this may takes a while ...")
                     progressDialog.show()


                     val mAuth = FirebaseAuth.getInstance()
                     mAuth.signInWithEmailAndPassword(email,pass)
                         .addOnCompleteListener { task->

                             if ( task.isSuccessful)
                             {

                                 val i = Intent(this@SignInActivity, MainActivity::class.java)
                                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                 startActivity(i)
                                 finish()
                             }else
                             {
                                 val msg = task.exception!!.toString()
                                 message(msg)
                                 FirebaseAuth.getInstance().signOut()
                                 progressDialog.dismiss()
                             }
                         }

                 }
             }


    }


    override fun onStart() {
        super.onStart()

if ( FirebaseAuth.getInstance().currentUser!=null)
{
    val i = Intent(this@SignInActivity, MainActivity::class.java)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(i)
    finish()
}





    }

    private fun message(ch: String) {
        Toast.makeText(this, ch, Toast.LENGTH_SHORT).show()
    }
}

