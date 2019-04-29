package com.fiap.giftgift.ui.login

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.giftgift.R
import com.fiap.giftgift.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        btCriar.setOnClickListener{
            mAuth.createUserWithEmailAndPassword(
                    etEmail.text.toString(),
                    etSenha.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    salvaNoRealTimeDatabase()
                } else {
                    Toast.makeText(this@SignUpActivity,
                            it.exception?.message,
                            Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    private fun salvaNoRealTimeDatabase() {
        var giftInit: ArrayList<String> = ArrayList()
        giftInit.add("teste gift 1")
        val user = Usuario(etNome.text.toString(), etEmail.text.toString(), etTelefone.text.toString())
        FirebaseDatabase.getInstance().getReference("Usuario")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener{
                    if (it.isSuccessful) {
                        Toast.makeText(this@SignUpActivity,"Usuário criado com sucesso!", Toast.LENGTH_LONG).show()
                        val intent = Intent()
                        intent.putExtra("email" ,etEmail.text.toString())
                        intent.putExtra("senha",etSenha.text.toString())

                        setResult(Activity.RESULT_OK,intent)
                        finish()
                    } else {
                        Toast.makeText(this@SignUpActivity,it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
    }


}
