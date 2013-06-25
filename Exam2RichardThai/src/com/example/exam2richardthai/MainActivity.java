package com.example.exam2richardthai;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements
		OnSharedPreferenceChangeListener {

	private TextView mTopText;
	private boolean mConfirmMessage;
	private static final int REQUEST_CODE_MSG_ACTIVITY = 1;
	public static final String TOP_MESSAGE = "TOP_MESSAGE";
	public static final String KEY_CONFIRM_MESSAGE = "KEY_CONFIRM_MESSAGE";
	public static final String E = "E";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTopText = (TextView) findViewById(R.id.textViewTop);
		mConfirmMessage = false;

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);

		updateConfirmationWithPreferences();
		Log.d(E, "onCreate value is " + mConfirmMessage);
	}

	private void updateConfirmationWithPreferences() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		mConfirmMessage = prefs.getBoolean(getString(R.string.key_confirm),
				false);
		Log.d(E, "value is " + mConfirmMessage);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			Log.d(E, "settings option pressed");
			Intent settingsIntent = new Intent(this, MessagePreferences.class);
			this.startActivity(settingsIntent);
			break;
		case R.id.add_message:
			Log.d(E, "add message option pressed");
			Intent askIntent = new Intent(this, MessageActivity.class);
			askIntent.putExtra(KEY_CONFIRM_MESSAGE, mConfirmMessage);
			this.startActivityForResult(askIntent, REQUEST_CODE_MSG_ACTIVITY);
			break;
		default:
			Log.d(E, "request code unrecognized");
			break;
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_MSG_ACTIVITY:
			if (resultCode == RESULT_OK) {
				mTopText.setText(data.getCharSequenceExtra(TOP_MESSAGE));
			} else {
				Log.d(E, "save message cancelled");
			}
			break;
		default:
			Log.d(E, "request code unrecognized");
			break;
		}
	}

	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		updateConfirmationWithPreferences();
	}
}
