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
                    //salvaNoRealTimeDatabase()
                    saveDatabase()
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

            Toast.makeText(this@SignUpActivity,"Usuário criado com sucesso!", Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.putExtra("email" ,etEmail.text.toString())
            intent.putExtra("senha",etSenha.text.toString())

            setResult(Activity.RESULT_OK,intent)
            finish()

        }.addOnFailureListener { e ->
            Toast.makeText(this@SignUpActivity,
                            "Error adding document" + e + "Error!! Fail to create / insert a new document !",
                            Toast.LENGTH_LONG).show()
        }

        /*
        var giftInit: ArrayList<String> = ArrayList()
        giftInit.add("")

        val user = HashMap<String, Any>()
        user["name"] = etNome.text.toString()
        user["email"] = etEmail.text.toString()
        user["phone"] = etTelefone.text.toString()
        user["gifts"] = giftInit

        db.collection("gifts")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this@SignUpActivity,"Usuário criado com sucesso!", Toast.LENGTH_LONG).show()
                    val intent = Intent()
                    intent.putExtra("email" ,etEmail.text.toString())
                    intent.putExtra("senha",etSenha.text.toString())
                    intent.putExtra("documentReference",documentReference.toString())

                    setResult(Activity.RESULT_OK,intent)
                    finish()

                    }
                .addOnFailureListener { e ->
                    Toast.makeText(this@SignUpActivity,
                            "Error adding document" + e + "Error!! Fail to create / insert a new document !",
                            Toast.LENGTH_LONG).show()
                }*/

    }
}
