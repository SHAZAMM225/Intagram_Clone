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
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)




        signUp_btn.setOnClickListener {
            CreateAccount()
        }

        already_have.setOnClickListener {

            val i = Intent(this@SignUpActivity, SignInActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
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
                            message("yesss")

saveInfoUser(fullName,userName,email,progressDialog)
                            message("yess 2s")

                        } else {
                            val msg = task.exception!!.toString()
                            message(msg)
                            mAuth.signOut()
                            progressDialog.dismiss()

                        }

                    }

            }


        }
    }

    private fun saveInfoUser(
        fullName: String,
        userName: String,
        email: String,
        progressDialog: ProgressDialog
    ) {


        try {
            val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
            val userRef = FirebaseDatabase.getInstance().reference.child("Users")

            val userMap = hashMapOf<String, Any>().apply {
                this["uid"] = currentUserId
                this["fullname"] = fullName
                this["username"] = userName
                this["email"] = email
                this["bio"] = "hiii"
                this["image"] = "https://firebasestorage.googleapis.com/v0/b/instaclone-8430b.appspot.com/o/Images%2Fprofile.png?alt=media&token=1b387cba-9912-4d92-922c-eac166f4a001"
            }

            userRef.child(currentUserId).setValue(userMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        message("Account has been created!")
                        val i = Intent(this@SignUpActivity, MainActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finish()
                    } else {
                        val msg = task.exception!!.toString()
                        message(msg)
                        FirebaseAuth.getInstance().signOut()
                        progressDialog.dismiss()
                    }
                }
        } catch (e: Exception) {
            progressDialog.dismiss()
            message("An error occurred: $e")
        }
    }


    private fun message(ch: String) {
        Toast.makeText(this, ch, Toast.LENGTH_SHORT).show()
    }
}