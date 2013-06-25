package edu.rosehulman.hellopreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements
		OnSharedPreferenceChangeListener {

	private TextView mLikeDogs;
	private TextView mLikeCats;
	private TextView mDessertSDK;
	private TextView mFavoriteColor;
	private TextView mClassDays;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mLikeDogs = (TextView) findViewById(R.id.do_you_like_dogs);
		mLikeCats = (TextView) findViewById(R.id.do_you_like_cats);
		mDessertSDK = (TextView) findViewById(R.id.dessert_sdk);
		mFavoriteColor = (TextView) findViewById(R.id.favorite_color);
		mClassDays = (TextView) findViewById(R.id.class_days);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);

		updateTextWithPreferences();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		updateTextWithPreferences();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, MyFavoritePreferences.class);
		this.startActivity(intent);
		return true;
	}

	private void updateTextWithPreferences() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		if (prefs.getBoolean(getString(R.string.key_dogs), false)) {
			mLikeDogs.setText(R.string.yes);
		} else {
			mLikeDogs.setText(R.string.no);
		}

		if (prefs.getBoolean(getString(R.string.key_cats), false)) {
			mLikeCats.setText(R.string.yes);
		} else {
			mLikeCats.setText(R.string.no);
		}

		mDessertSDK.setText(prefs.getString(
				getString(R.string.key_dessert_sdk), "---"));

		mFavoriteColor.setText(prefs.getString(
				getString(R.string.key_favorite_color), "---"));

		String classDays = "";
		classDays = prefs.getBoolean(getString(R.string.key_monday), false) ? classDays
				+ getString(R.string.monday) + " "
				: classDays;
		classDays = prefs.getBoolean(getString(R.string.key_tuesday), false) ? classDays
				+ getString(R.string.tuesday) + " "
				: classDays;
		classDays = prefs.getBoolean(getString(R.string.key_wednesday), false) ? classDays
				+ getString(R.string.wednesday) + " "
				: classDays;
		classDays = prefs.getBoolean(getString(R.string.key_thursday), false) ? classDays
				+ getString(R.string.thursday) + " "
				: classDays;
		classDays = prefs.getBoolean(getString(R.string.key_friday), false) ? classDays
				+ getString(R.string.friday) + " "
				: classDays;
		mClassDays.setText(classDays);
	}
}
