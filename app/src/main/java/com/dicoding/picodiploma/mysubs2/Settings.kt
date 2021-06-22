package com.dicoding.picodiploma.mysubs2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction().add(R.id.setting_holder, SettingPreference()).commit()
    }
}