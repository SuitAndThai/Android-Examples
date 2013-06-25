package com.example.basketballjersey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class JerseyInfo extends Activity implements OnClickListener {

	private EditText mNameEditText;
	private EditText mNumberEditText;
	private ToggleButton mJerseyIsRedToggleButton;
	private Button mCancelButton;
	private Button mOkButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jersey_info);

		mNameEditText = (EditText) findViewById(R.id.jersey_info_name_edit_text);
		mNumberEditText = (EditText) findViewById(R.id.jersey_info_number_edit_text);
		mJerseyIsRedToggleButton = (ToggleButton) findViewById(R.id.jersey_info_toggle_color_button);
		mCancelButton = (Button) findViewById(R.id.jersey_info_cancel_button);
		mOkButton = (Button) findViewById(R.id.jersey_info_ok_button);

		Intent data = this.getIntent();
		String name = data
				.getStringExtra(JerseyDisplayActivity.KEY_PLAYER_NAME);
		String number = ""
				+ data.getIntExtra(JerseyDisplayActivity.KEY_PLAYER_NUMBER, 0);
		boolean isRed = data.getBooleanExtra(
				JerseyDisplayActivity.KEY_JERSEY_IS_RED, false);

		mNameEditText.setText(name);
		mNumberEditText.setText(number);
		mJerseyIsRedToggleButton.setChecked(isRed);

		mCancelButton.setOnClickListener(this);
		mOkButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_jersey_info, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jersey_info_ok_button:
			Log.d(JerseyDisplayActivity.JDA, "ok button clicked");

			Intent result = new Intent();
			String name = "";
			int number = 0;
			boolean isRed = false;
			name = mNameEditText.getText().toString();
			String numberString = mNumberEditText.getText().toString();
			try {
				number = Integer.parseInt(numberString);
			} catch (NumberFormatException e) {
				Log.e(JerseyDisplayActivity.JDA, "Invalid number format");
			}
			isRed = mJerseyIsRedToggleButton.isChecked();
			this.setResult(RESULT_OK, result);

			Log.d(JerseyDisplayActivity.JDA, "name = " + name + ", number = "
					+ number + ", is checked = " + isRed);

			result.putExtra(JerseyDisplayActivity.KEY_PLAYER_NAME, name);
			result.putExtra(JerseyDisplayActivity.KEY_PLAYER_NUMBER, number);
			result.putExtra(JerseyDisplayActivity.KEY_JERSEY_IS_RED, isRed);

			this.finish();
			break;

		case R.id.jersey_info_cancel_button:
			Log.d(JerseyDisplayActivity.JDA, "cancel button clicked");
			this.setResult(RESULT_CANCELED);
			this.finish();
			break;

		default:
			Log.d(JerseyDisplayActivity.JDA, "unrecognized id " + v.getId()
					+ " clicked");
			break;
		}
	}
}
