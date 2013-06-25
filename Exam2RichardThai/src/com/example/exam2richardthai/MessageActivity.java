package com.example.exam2richardthai;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends FragmentActivity implements
		OnClickListener, OnSharedPreferenceChangeListener {

	private EditText editText;
	private Button saveButton;
	private boolean mConfirmationNeeded;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messsage);

		editText = (EditText) findViewById(R.id.editText);
		saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DialogFragment df = new DialogFragment() {
					@Override
					public Dialog onCreateDialog(Bundle savedInstanceState) {
						AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(
								MessageActivity.this);

						Resources res = getResources();
						String text = String.format(
								res.getString(R.string.msg_description),
								editText.getText().toString());

						aboutDialogBuilder.setMessage(text);
						aboutDialogBuilder
								.setTitle(R.string.confirm_message_title);
						aboutDialogBuilder.setCancelable(true);
						aboutDialogBuilder.setPositiveButton(R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialogInt, int which) {
										dialogInt.dismiss();
										pullText();
									}
								});
						return aboutDialogBuilder.create();
					}
				};

				Log.d(MainActivity.E, "dialog value is " + mConfirmationNeeded);

				if (mConfirmationNeeded) {
					df.show(getSupportFragmentManager(), "");
					Log.d(MainActivity.E, "dialog should have shown");
				} else {
					pullText();
					Log.d(MainActivity.E, "dialog didn't show");

				}
			}

			private void pullText() {
				Toast.makeText(getApplicationContext(),
						getString(R.string.confirming_selection),
						Toast.LENGTH_SHORT).show();

				Intent result = new Intent();
				String returnString = editText.getText().toString();
				result.putExtra(MainActivity.TOP_MESSAGE, returnString);
				MessageActivity.this.setResult(RESULT_OK, result);
				MessageActivity.this.finish();
			}
		});

		Intent data = this.getIntent();
		mConfirmationNeeded = data.getBooleanExtra(
				MainActivity.KEY_CONFIRM_MESSAGE, false);

		Log.d(MainActivity.E, "MessageQActivity value is "
				+ mConfirmationNeeded);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View v) {
		DialogFragment df = new DialogFragment() {
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(
						MessageActivity.this);
				aboutDialogBuilder.setMessage(R.string.msg_description);
				aboutDialogBuilder.setTitle(R.string.confirm_message_title);
				aboutDialogBuilder.setCancelable(true);
				aboutDialogBuilder.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogInt,
									int which) {
								dialogInt.dismiss();
							}
						});
				return aboutDialogBuilder.create();
			}
		};

		Log.d(MainActivity.E, "dialog value is " + mConfirmationNeeded);

		if (mConfirmationNeeded) {
			df.show(getSupportFragmentManager(), "");
		}

		Intent result = new Intent();
		String returnString = editText.getText().toString();
		result.putExtra(MainActivity.TOP_MESSAGE, returnString);
		this.setResult(RESULT_OK, result);
		this.finish();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub

	}
}
