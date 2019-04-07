package com.fiap.giftgift

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val CADASTRO_REQUEST_CODE = 1
    private lateinit var maAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        maAuth = FirebaseAuth.getInstance()

        if (maAuth.currentUser != null) {
            vaiParaTelaMenu()
        }

        btLogar.setOnClickListener{
            if (etEmail.text.isEmpty() || etSenha.text.isEmpty() ){
                Toast.makeText(this@LoginActivity, "Usuário e Senha obrigatórios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            maAuth.signInWithEmailAndPassword(etEmail.text.toString(),etSenha.text.toString()).addOnCompleteListener({
                if (it.isSuccessful) {
                    vaiParaTelaMenu()
                } else {
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            })
        }

        btNovaConta.setOnClickListener{
         //   val telaSeguinte = Intent(this,SignUpActivity::class.java)
         //   startActivityForResult(telaSeguinte,CADASTRO_REQUEST_CODE)
        }

    }

    private fun vaiParaTelaMenu(){
        val telaSeguinte = Intent(this,MainActivity::class.java)
        startActivity(telaSeguinte)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CADASTRO_REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK ->  {
                        etEmail.setText(data?.getStringExtra("email"))
                        etSenha.setText(data?.getStringExtra("senha"))
                    }
                }
            }
        }
    }

}