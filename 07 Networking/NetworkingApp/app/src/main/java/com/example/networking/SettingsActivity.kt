package com.example.networking


import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager

import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    class EarthquakePreferenceFragment : PreferenceFragment(),
        Preference.OnPreferenceChangeListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings_main)

            val minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key))
            bindPreferenceSummaryToValue(minMagnitude)
            val orderBy = findPreference(getString(R.string.settings_order_by_key))
            bindPreferenceSummaryToValue(orderBy)
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this
            val preferences = PreferenceManager.getDefaultSharedPreferences(preference.context)
            val preferenceString = preferences.getString(preference.key, "")
            onPreferenceChange(preference, preferenceString!!)
        }

        override fun onPreferenceChange(preference: Preference, value: Any): Boolean {
            val stringValue = value.toString()
            if (preference is ListPreference) {
                val prefIndex = preference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) {
                    val labels = preference.entries
                    preference.setSummary(labels[prefIndex])
                }
            } else {
                preference.summary = stringValue
            }
            return true
        }
    }

}
