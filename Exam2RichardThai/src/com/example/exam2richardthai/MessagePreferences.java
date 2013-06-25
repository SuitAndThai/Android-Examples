package com.example.exam2richardthai;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class MessagePreferences extends PreferenceActivity {

	private CheckBoxPreference mConfirmPref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.message_preferences);

		mConfirmPref = (CheckBoxPreference) findPreference(getString(R.string.key_confirm));
	}

	@Override
	@Deprecated
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		super.onPreferenceTreeClick(preferenceScreen, preference);
		return true;
	}
}
