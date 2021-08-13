package com.sungshin.recyclearuser.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sungshin.recyclearuser.MainActivity
import com.sungshin.recyclearuser.databinding.ActivitySigninBinding
import com.sungshin.recyclearuser.signup.SignupActivity

class SigninActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySigninBinding
    private lateinit var auth: FirebaseAuth

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.edittextSigninId.setText(it.data?.getStringExtra("id"))
        binding.edittextSigninPw.setText(it.data?.getStringExtra("pw"))

        binding.edittextSigninId.setText(it.data?.getStringExtra("signin_id"))
        binding.edittextSigninPw.setText(it.data?.getStringExtra("signin_pw"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        buttonEvents()
    }

    private fun buttonEvents(){
        binding.buttonSigninLogin.setOnClickListener{
            val email = binding.edittextSigninId.text.toString()
            val pw = binding.edittextSigninPw.text.toString()
            if (email.isNotEmpty() && pw.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, pw).addOnCompleteListener {
                        task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext,
                            "로그인 성공!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SigninActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(applicationContext,
                            "로그인 실패!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(applicationContext,
                    "아이디 혹은 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textviewSigninGosignup.setOnClickListener {
            val intent = Intent(this@SigninActivity, SignupActivity::class.java)
            signUpActivityLauncher.launch(intent)
            finish()
        }

        binding.checkboxSigninId.setOnClickListener{
            val email = binding.edittextSigninId.text.toString()
            val intent = Intent()
            intent.putExtra("signin_id", email)
        }

        binding.checkboxSigninPw.setOnClickListener{
            val pw = binding.edittextSigninPw.text.toString()
            val intent = Intent()
            intent.putExtra("signin_pw", pw)
        }
    }
}
