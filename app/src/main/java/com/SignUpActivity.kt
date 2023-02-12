package com

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.instagram_clone.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)




        sign_up.setOnClickListener {
            CreateAccount()
        }
    }


    private fun CreateAccount() {

        val fullName = full_name_up.text.toString()
        val userName = username_up.text.toString()
        val email = email_sign_up.text.toString()
        val pass = pass_up.text.toString()

        when {
            TextUtils.isEmpty(fullName) -> {
                message("full name is Empty ")
            }
            TextUtils.isEmpty(userName) -> {
                message("user name is Empty ")
            }
            TextUtils.isEmpty(email) -> {
                message("email is Empty ")
            }
            TextUtils.isEmpty(pass) -> {
                message("pass name is Empty ")
            }
            else -> {


                val progressDialog=ProgressDialog(this@SignUpActivity)
                progressDialog.setTitle("SignUp")
                progressDialog.setMessage("Please Wait ,this may takes a while ...")
                progressDialog.show()

                val mAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

saveInfoUser(fullName,userName,email)
                        } else {
                            val msg = task.exception!!.toString()
                            message(msg)
                            progressDialog.dismiss()

                        }

                    }

            }


        }
    }

    private fun saveInfoUser(fullName: String, userName: String, email: String) {



    }


    private fun message(ch: String) {
        Toast.makeText(this, ch, Toast.LENGTH_SHORT).show()
    }
}