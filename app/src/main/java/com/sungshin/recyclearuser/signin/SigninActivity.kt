package com.sungshin.recyclearuser.signin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sungshin.recyclearuser.MainActivity
import com.sungshin.recyclearuser.databinding.ActivitySigninBinding
import com.sungshin.recyclearuser.signup.SignupActivity
import com.sungshin.recyclearuser.utils.MyPref

class SigninActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySigninBinding
    private lateinit var auth: FirebaseAuth

    var isIDcheckBoxChecked = false
    var isPWcheckBoxChecked = false

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

        val saveIDdata = MyPref.prefs.getString("id", " ")
        val savePWdata = MyPref.prefs.getString("pw", " ")

        if (saveIDdata != " ") {
            binding.edittextSigninId.setText(saveIDdata)
            binding.checkboxSigninId.setChecked(true)
        }

        if (savePWdata != " ") {
            binding.edittextSigninPw.setText(savePWdata)
            binding.checkboxSigninPw.setChecked(true)
        }
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
//            finish()
        }

        binding.checkboxSigninId.setOnClickListener {
            val pref = this.getPreferences(0)
            val prefEditor = pref.edit()
            val saveIDdata = pref.getString("prefId", "")

            if (saveIDdata != null) {
                Log.e("checkbox id", saveIDdata)
            }

            isIDcheckBoxChecked = false

            if (binding.checkboxSigninId.isChecked) {
                isIDcheckBoxChecked = true
                MyPref.prefs.setString("id", binding.edittextSigninId.text.toString())
            }

            else {
                if (isPWcheckBoxChecked) {
                    Log.e("check", "id x pw o")
                    Toast.makeText(this, "비밀번호 저장을 먼저 해제해주세요", Toast.LENGTH_SHORT).show()
                    isIDcheckBoxChecked = true
                    binding.checkboxSigninId.setChecked(true)
                }

                else {
                    isIDcheckBoxChecked = false
                    MyPref.prefs.setString("id", " ")
                }
            }
        }

        binding.checkboxSigninPw.setOnClickListener {
            val pref = this.getPreferences(0)
            val prefEditor = pref.edit()
            val savePWdata = pref.getString("prefPw", " ")
            isPWcheckBoxChecked = false
            if (savePWdata != null) {
                Log.e("check pw", savePWdata)
            }

            else {
                Log.e("check pw", "null")
            }

            if (binding.checkboxSigninPw.isChecked) {
                if (!isIDcheckBoxChecked) {
                    Log.e("check", "id x pw o")
                    Toast.makeText(this, "아이디 저장을 먼저 선택해주세요", Toast.LENGTH_SHORT).show()
                    isPWcheckBoxChecked = false
                    binding.checkboxSigninPw.setChecked(false)
                }

                else {
                    isPWcheckBoxChecked = true
                    MyPref.prefs.setString("pw", binding.edittextSigninPw.text.toString())
                }
            }

            else {
                isPWcheckBoxChecked = false
                MyPref.prefs.setString("pw", " ")
            }
        }
    }
}
