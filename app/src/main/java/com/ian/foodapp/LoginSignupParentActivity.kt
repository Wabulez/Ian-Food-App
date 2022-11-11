package com.ian.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class LoginSignupParentActivity : AppCompatActivity() {
    var loginSignUpParent_page: ConstraintLayout?= null
    var viewPager: ViewPager?= null
    var tabLayout: TabLayout?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup_parent)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        loginSignUpParent_page = findViewById(R.id.login_signup_parent)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Login"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Register"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginSignupAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}
