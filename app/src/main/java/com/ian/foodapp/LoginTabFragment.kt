package com.ian.foodapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class LoginTabFragment:Fragment() {
    var email: EditText?= null
    var password: EditText?= null
    var forgotPasswordTxt: TextView?= null
    var loginBtn: Button?= null
    var loginLayout: ConstraintLayout?= null
    var progressDialog:ProgressDialog ?= null
    var handler: Handler?= null
    var runnable:Runnable ?= null
    //email pattern used to check proper email format
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.login_tab_fragment, container, false)
        email = root.findViewById(R.id.login_email)
        password = root.findViewById(R.id.login_password)
        forgotPasswordTxt = root.findViewById(R.id.forgot_password)
        loginBtn = root.findViewById(R.id.login_btn)
        loginLayout = root.findViewById(R.id.login_fragment) //this is here so that we can use snackbar popups. They need a target layout, unlike a Toast popup

        //Loading dialog as app checks info/logs user in
        progressDialog = ProgressDialog(getActivity())
        progressDialog!!.setTitle("Please Wait...")
        progressDialog!!.setMessage("Logging In...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)



        loginBtn!!.setOnClickListener {
            var uEmail = email!!.text.toString().trim()
            var uPassword = password!!.text.toString().trim()

            if (uEmail.isEmpty()){
                email!!.setError("Email required")
                email!!.requestFocus()
            }
            else if (!uEmail.matches(emailPattern.toRegex())){
                email!!.setError("Invalid email address")
                email!!.requestFocus()
            }
            else if (uPassword.isEmpty()){
                password!!.setError("Password required")
                password!!.requestFocus()
            }
            else{
                //You can remove slashes when you link firebase and use this code or just remove it and use different code.


//                progressDialog!!.show()
//                auth!!.signInWithEmailAndPassword(uEmail,uPassword).addOnCompleteListener {
//                    if (it.isSuccessful){
//                        progressDialog!!.dismiss()
                        email!!.text.clear()
                        password!!.text.clear()
                        var snackbar = Snackbar.make(loginLayout!!,"Login successful", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                //handler used to run a process after a certain time delay.
                //Must be called again to work
                        handler = Handler()
                //runnable used to determine the process that will be run after time delay
                //Must be called with handler to work. Currently not doing anything
                        runnable = object : Runnable {
                            override fun run() {
                                var i = Intent(getActivity(), MainActivity::class.java).apply {
                                    putExtra("Login","Login")
                                }
                                startActivity(i)
                                activity?.finish()
                            }
                        }
                        //handler!!.postDelayed(runnable!!,500) //Delay is measured in milliseconds. 500 (0.5 seconds) in this case
                    }
//                }.addOnFailureListener {
//                    progressDialog!!.dismiss()
//                    Snackbar.make(loginLayout!!,it.message.toString(), Snackbar.LENGTH_LONG).show()
//                }
//            }
        }

        return root
    }
}