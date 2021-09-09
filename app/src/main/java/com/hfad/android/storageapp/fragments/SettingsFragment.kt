package com.hfad.android.storageapp.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.hfad.android.storageapp.R

class SettingsFragment:PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }


}