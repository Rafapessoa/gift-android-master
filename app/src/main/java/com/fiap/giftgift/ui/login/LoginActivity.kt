package com.fiap.giftgift.ui.login

import android.app.Activity
import android.app.NativeActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.giftgift.ui.main.NavigationActivity

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



import com.google.firebase.auth.AuthResult

import com.google.android.gms.tasks.OnCompleteListener


class LoginActivity : AppCompatActivity() {

    private val CADASTRO_REQUEST_CODE = 1
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fiap.giftgift.R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            vaiParaTelaMenu()
        }

        btLogar.setOnClickListener{

            val email = etEmail.text.toString()
            val password = etSenha.text.toString()


            if (email.isEmpty() || password.isEmpty() ){
                Toast.makeText(this@LoginActivity, "Usuário e Senha obrigatórios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            /*
            mAuth.signInWithEmailAndPassword(etEmail.text.toString(),etSenha.text.toString()).addOnCompleteListener({
                if (it.isSuccessful) {
                    vaiParaTelaMenu()
                } else {
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            })
*/
            try {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {

                                vaiParaTelaMenu()
                            } else {

                                Toast.makeText(this@LoginActivity, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                vaiParaTelaMenu()
                            }


                        })
            } catch(t: Throwable) {
                Toast.makeText(this@LoginActivity, "Authentication failed. " + t.message,
                        Toast.LENGTH_SHORT).show()
            }


            vaiParaTelaMenu() //TODO temp

        }

        btNovaConta.setOnClickListener{
            val telaSeguinte = Intent(this, SignUpActivity::class.java)
            startActivityForResult(telaSeguinte,CADASTRO_REQUEST_CODE)
        }



    }

    private fun vaiParaTelaMenu(){
        val telaSeguinte = Intent(this, NavigationActivity::class.java)
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