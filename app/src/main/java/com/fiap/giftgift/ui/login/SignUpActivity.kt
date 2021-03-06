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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    val db = FirebaseFirestore.getInstance()

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
                    saveDatabase()
                } else {
                    Toast.makeText(this@SignUpActivity,
                            it.exception?.message,
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun saveDatabase() {

        val user = db.collection("users")
        var giftInit: ArrayList<String> = ArrayList()
        giftInit.add("")

        val userInfo = HashMap<String, Any>()
        userInfo["name"] = etNome.text.toString()
        userInfo["email"] = etEmail.text.toString()
        userInfo["phone"] = etTelefone.text.toString()
        userInfo["gifts"] = giftInit

        user.document( etEmail.text.toString() ).set(userInfo).addOnSuccessListener {

            Toast.makeText(this@SignUpActivity,getString(R.string.auth_create_user), Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.putExtra("email" ,etEmail.text.toString())
            intent.putExtra("senha",etSenha.text.toString())

            setResult(Activity.RESULT_OK,intent)
            finish()

        }.addOnFailureListener { e ->
            Toast.makeText(this@SignUpActivity,
                    getString(R.string.auth_fail) + e  ,
                            Toast.LENGTH_LONG).show()
        }


    }
}
