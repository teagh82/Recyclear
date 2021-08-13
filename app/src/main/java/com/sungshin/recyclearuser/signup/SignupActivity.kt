package com.sungshin.recyclearuser.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sungshin.recyclearuser.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        buttonEvents()
    }

    private fun buttonEvents(){
        binding.buttonSignup.setOnClickListener {
            val email = binding.edittextSignupId.text.toString()
            val name = binding.edittextSignupName.text.toString()
            val pw = binding.edittextSignupPw.text.toString()
            val pwCheck = binding.edittextSignupPwcheck.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "이메일을 입력해주세요.", Toast.LENGTH_SHORT
                ).show()
            } else if (name.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "별명을 입력해주세요.", Toast.LENGTH_SHORT
                ).show()
            } else if (pw.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT
                ).show()
            } else if (pwCheck.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "비밀번호가 동일하지 않습니다.", Toast.LENGTH_SHORT
                ).show()
            } else if (pw.length < 5) {
                Toast.makeText(
                    applicationContext,
                    "비밀번호를 6자리 이상으로 입력해주세요.", Toast.LENGTH_SHORT
                ).show()
            }

            if (email.isNotEmpty() && name.isNotEmpty() && pw.isNotEmpty() && pw == pwCheck) {
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val database =
                                Firebase.database("https://recyclear-user-c81c3-default-rtdb.asia-southeast1.firebasedatabase.app/")

                            val emailFront = email.split('.')[0]

                            val myRefMail =
                                database.getReference("User").child(emailFront).child("email")
                            myRefMail.setValue(email)

                            val myRefName =
                                database.getReference("User").child(emailFront).child("name")
                            myRefName.setValue(name)

                            val myRefPoints =
                                database.getReference("User").child(emailFront).child("points")
                            myRefPoints.setValue(0)

                            Toast.makeText(
                                applicationContext,
                                "회원가입에 성공하였습니다.\n로그인을 진행해주세요", Toast.LENGTH_SHORT
                            ).show()
//                            startActivity(Intent(this, SigninActivity::class.java))
//                            finish()

                            val intent = Intent()
                            intent.putExtra("name", name)
                            intent.putExtra("id", email)
                            intent.putExtra("pw", pw)
                            setResult(Activity.RESULT_OK, intent)
                            finish()

                        } else {
                            Toast.makeText(
                                applicationContext,
                                "회원가입 실패!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}