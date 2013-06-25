package com.example.basketballjersey;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JerseyDisplayActivity extends Activity implements OnClickListener {

	public static final String JDA = "JDA";
	private ImageView mJerseyImageView;
	private TextView mNameTextView;
	private TextView mNumberTextView;
	private String mPlayerName = "Dave";
	private int mPlayerNumber = 14;
	private boolean mJerseyIsRed = false;
	private Button mEditButton;
	public static final String KEY_PLAYER_NAME = "KEY_PLAYER_NAME";
	public static final String KEY_PLAYER_NUMBER = "KEY_PLAYER_NUMBER";
	public static final String KEY_JERSEY_IS_RED = "KEY_JERSEY_IS_RED";
	private static final int REQUEST_CODE_JERSEY_INFO = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jersey_display);

		mJerseyImageView = (ImageView) findViewById(R.id.jersey_image);
		mNameTextView = (TextView) findViewById(R.id.jersey_display_name);
		mNumberTextView = (TextView) findViewById(R.id.jersey_display_number);
		updateJerseyInfo();

		mEditButton = (Button) findViewById(R.id.jersey_display_edit_button);
		mEditButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jersey_display_edit_button:
			Log.d(JDA, "Clicked the edit button");
			Intent infoIntent = new Intent(this, JerseyInfo.class);

			infoIntent.putExtra(KEY_PLAYER_NAME, mPlayerName);
			infoIntent.putExtra(KEY_PLAYER_NUMBER, mPlayerNumber);
			infoIntent.putExtra(KEY_JERSEY_IS_RED, mJerseyIsRed);

			this.startActivityForResult(infoIntent, REQUEST_CODE_JERSEY_INFO);
			break;
		default:
			Log.d(JerseyDisplayActivity.JDA, "unrecognized id " + v.getId()
					+ " clicked");
			break;
		}
	}

	private void updateJerseyInfo() {
		if (mJerseyIsRed) {
			mJerseyImageView.setImageResource(R.drawable.red_jersey);
		} else {
			mJerseyImageView.setImageResource(R.drawable.blue_jersey);
		}

		mNameTextView.setText(mPlayerName);
		mNumberTextView.setText("" + mPlayerNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_jersey_display, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case REQUEST_CODE_JERSEY_INFO:
			if (resultCode == RESULT_OK) {

				mPlayerName = data.getStringExtra(KEY_PLAYER_NAME);
				mPlayerNumber = data.getIntExtra(KEY_PLAYER_NUMBER, 0);
				mJerseyIsRed = data.getBooleanExtra(KEY_JERSEY_IS_RED, false);
				updateJerseyInfo();
			} else {
				Log.d(JDA, "Result not OK because cancelled");
			}
			break;

		default:
			Log.d(JDA, "Request code unrecognized!");
			break;
		}
	}
}
