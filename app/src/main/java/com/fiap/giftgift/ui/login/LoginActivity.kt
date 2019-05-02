package com.fiap.giftgift.ui.login

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.fiap.giftgift.ui.main.NavigationActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {

    private val CADASTRO_REQUEST_CODE = 1
    var fbAuth = FirebaseAuth.getInstance()
    var documentReference = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fiap.giftgift.R.layout.activity_login)

        var btnLogin = findViewById<Button>(com.fiap.giftgift.R.id.btLogar)
        btnLogin.setOnClickListener {view ->
            signIn(view,etEmail.text.toString(), etSenha.text.toString())
        }

        var btnCreate = findViewById<Button>(com.fiap.giftgift.R.id.btNovaConta)
        btnCreate.setOnClickListener {view ->
            createAcc(view)
        }


        if (fbAuth.currentUser != null) {

            val user = fbAuth.currentUser
            val email: String


            if (user != null) {
                email = user.email!!
            }
            vaiParaTelaMenu()
        }
    }

    fun signIn(view: View,email: String, password: String){


        if (email.isEmpty() || password.isEmpty() ){
            Toast.makeText(this@LoginActivity, "Usuário e Senha obrigatórios", Toast.LENGTH_LONG).show()
            return
        }

        showMessage(view,"Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){

                vaiParaTelaMenu()

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })

    }

    private fun createAcc(view: View){
        val telaSeguinte = Intent(this, SignUpActivity::class.java)

        startActivityForResult(telaSeguinte,CADASTRO_REQUEST_CODE)
        if (fbAuth.currentUser != null) {
            vaiParaTelaMenu()
        }
    }

    private fun vaiParaTelaMenu(){
        val telaSeguinte = Intent(this, NavigationActivity::class.java)
        startActivity(telaSeguinte)

    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CADASTRO_REQUEST_CODE -> {
                when(resultCode){
                    Activity.RESULT_OK ->  {

                        etEmail.setText(data?.getStringExtra("email"))
                        etSenha.setText(data?.getStringExtra("senha"))

                        vaiParaTelaMenu()
                    }
                }
            }
        }
    }

}