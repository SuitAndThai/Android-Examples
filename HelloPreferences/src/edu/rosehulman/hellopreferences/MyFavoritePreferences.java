package edu.rosehulman.hellopreferences;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class MyFavoritePreferences extends PreferenceActivity {

	private CheckBoxPreference mDogPrefs;
	private CheckBoxPreference mCatPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.my_favorite_preferences);

		mDogPrefs = (CheckBoxPreference) findPreference(getString(R.string.key_dogs));
		mCatPrefs = (CheckBoxPreference) findPreference(getString(R.string.key_cats));

		updatePreferences();
	}

	private void updatePreferences() {
		this.mCatPrefs.setShouldDisableView(true);
		if (this.mDogPrefs.isChecked()) {
			this.mCatPrefs.setChecked(false);
			this.mCatPrefs.setEnabled(false);
		} else {
			this.mCatPrefs.setEnabled(true);
		}
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		super.onPreferenceTreeClick(preferenceScreen, preference);
		if (preference == this.mDogPrefs)
			updatePreferences();
		return true;
	}
}
