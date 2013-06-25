package com.example.android.snake;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.widget.EditText;

public class SnakePreferences extends PreferenceActivity {
	private Preference mResumePref;
	private EditTextPreference mSpeedPref;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.snake_preferences);

		mResumePref = (Preference) findPreference(getString(R.string.key_resume));
		mSpeedPref = (EditTextPreference) findPreference(getString(R.string.key_starting_speed));

		EditText speedEditText = (EditText) mSpeedPref.getEditText();
		speedEditText
				.setKeyListener(DigitsKeyListener.getInstance(false, true));

		Intent data = this.getIntent();
		boolean isPaused = data.getBooleanExtra(Snake.GAME_IS_PAUSED, false);
		if (isPaused) {
			Log.d(Snake.SNAKE, "game is paused");
			mResumePref.setShouldDisableView(false);
			mResumePref.setEnabled(isPaused);
		} else {
			Log.d(Snake.SNAKE, "game is not paused");
			mResumePref.setShouldDisableView(true);
			mResumePref.setEnabled(isPaused);
		}
	}

	@Override
	@Deprecated
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (mResumePref == preference) {
			Intent result = new Intent();
			this.setResult(RESULT_OK, result);
			this.finish();
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

}
