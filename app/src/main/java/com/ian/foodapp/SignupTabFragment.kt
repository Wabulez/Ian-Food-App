package com.ian.foodapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class SignupTabFragment:Fragment() {
    var firstname: EditText?= null
    var email: EditText?= null
    var secondname: EditText?= null
    var password: EditText?= null
    var confirmPassword: EditText?= null
    var registerBtn: Button?= null
    var registerLayout: ConstraintLayout?= null
    //email pattern used to check proper email format
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.signup_tab_fragment, container, false)
        firstname = root.findViewById(R.id.signup_firstname)
        email = root.findViewById(R.id.signup_email)
        secondname = root.findViewById(R.id.signup_secondname)
        password = root.findViewById(R.id.signup_password)
        confirmPassword = root.findViewById(R.id.signup_confirm_password)
        registerBtn = root.findViewById(R.id.register_btn)
        //this register layout is here so that we can use snackbar popups. They need a target layout, unlike a Toast popup
        //feel free to ignore/remove if you don't want to use it
        registerLayout = root.findViewById(R.id.signup_fragment)

        registerBtn!!.setOnClickListener {
            var uFName = firstname!!.text.toString().trim()
            var uSName = secondname!!.text.toString().trim()
            var uEmail = email!!.text.toString().trim()
            var uPassword = password!!.text.toString().trim()
            var uConPassword = confirmPassword!!.text.toString().trim()

            if (uFName.isEmpty()) {
                firstname!!.setError("Name required")
                firstname!!.requestFocus()
            } else if (uSName.isEmpty()) {
                secondname!!.setError("Phone Number required")
                secondname!!.requestFocus()
            } else if (uEmail.isEmpty()) {
                email!!.setError("Email address required")
                email!!.requestFocus()
            } else if (!uEmail.matches(emailPattern.toRegex())) {
                email!!.setError("Invalid email address")
                email!!.requestFocus()
            } else if (uPassword.isEmpty()) {
                password!!.setError("Password required")
                password!!.requestFocus()
            } else if (uPassword.length < 6) {
                password!!.setError("Password too short")
                password!!.requestFocus()
            } else if (!uConPassword.equals(uPassword) || uConPassword.isEmpty()) {
                confirmPassword!!.setError("Passwords do not match")
                confirmPassword!!.requestFocus()
            } else {
                firstname!!.text.clear()
                secondname!!.text.clear()
                email!!.text.clear()
                password!!.text.clear()
                confirmPassword!!.text.clear()

                //used to move to another activity and carry values between activities
//                val i = Intent(getActivity(), MainActivity::class.java).apply {
//
//                }
                Snackbar.make(registerLayout!!,"Login Successful",Snackbar.LENGTH_LONG).show()
//                startActivity(i)
//                activity?.finish()
            }
        }
        return root
    }
}