package edu.rosehulman.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity implements OnClickListener {

	public static final String LLOM = "LLOM";
	static final String KEY_NUM_BUTTONS = "KEY_NUM_BUTONS";
	private int mNumButtons = 7;
	private static final int REQUEST_CODE_CHANGE_NUM_BUTTONS = 1;
	private static final String PREFS = "PREFS";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		SharedPreferences prefs = getSharedPreferences(PREFS,
				Activity.MODE_PRIVATE);
		mNumButtons = prefs.getInt(KEY_NUM_BUTTONS, 7);

		Button b = ((Button) findViewById(R.id.play_button));

		b.setOnClickListener(this);
		((Button) findViewById(R.id.change_num_buttons_button))
				.setOnClickListener(this);
		((Button) findViewById(R.id.about_button)).setOnClickListener(this);
		((Button) findViewById(R.id.exit_button)).setOnClickListener(this);

		Resources res = getResources();
		b.setText(res.getString(R.string.play_format, mNumButtons));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences prefs = this.getSharedPreferences(PREFS,
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = prefs.edit();
		prefsEditor.putInt(KEY_NUM_BUTTONS, mNumButtons);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play_button:
			Log.d(LLOM, "Play button clicked");
			Intent playIntent = new Intent(this, LightsOutActivity.class);
			break;
		case R.id.change_num_buttons_button:
			Log.d(LLOM, "Change button clicked");
			Intent numButtonsIntent = new Intent(this, ChangeNumButtons.class);
			numButtonsIntent.putExtra(KEY_NUM_BUTTONS, mNumButtons);
			this.startActivityForResult(numButtonsIntent,
					REQUEST_CODE_CHANGE_NUM_BUTTONS);
			break;
		case R.id.about_button: 
			Log.d(LLOM, "About button clicked");
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			this.startActivity(aboutIntent);
			break;
		case R.id.exit_button:
			Log.d(LLOM, "Exit button clicked");
			break;
		default:
			// Nothing
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_CHANGE_NUM_BUTTONS:
			if (resultCode == RESULT_OK) {
				mNumButtons = data.getIntExtra(KEY_NUM_BUTTONS, 7);
				Log.d(LLOM, "Got back " + mNumButtons + " buttons");

				Resources res = getResources();
				Button mPlayButton = (Button) findViewById(R.id.play_button);
				mPlayButton.setText(res.getString(R.string.play_format,
						mNumButtons));
			} else {
				Log.d(LLOM, "Result not OK because cancelled");
			}
			break;
		default:
			Log.d(LLOM, "Request code unrecognized!");
			break;
		}
	}
}