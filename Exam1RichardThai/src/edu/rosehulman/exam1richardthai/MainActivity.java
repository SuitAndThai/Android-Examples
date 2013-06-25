package edu.rosehulman.exam1richardthai;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

@TargetApi(11)
public class MainActivity extends Activity {

	private TextView mTextView;
	private EditText mEditText;
	private Button mShoutButton;
	private Button mEraseInputButton;
	private Button mStartOverButton;
	private int clicks;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.textView);
		mEditText = (EditText) findViewById(R.id.editText);
		mShoutButton = (Button) findViewById(R.id.shout_button);
		mEraseInputButton = (Button) findViewById(R.id.erase_input_button);
		mStartOverButton = (Button) findViewById(R.id.start_over_button);

		clicks = 0;

		mShoutButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String outputText = "";

				if (0 == clicks) {
					outputText = mEditText.getText().toString() + "!";
					mTextView.setText(outputText);
					mTextView.setTextSize(20);
					clicks++;
				}

				else if (1 == clicks) {
					outputText = mEditText.getText().toString() + "!!";
					mTextView.setText(outputText);
					mTextView.setTextSize(30);
					clicks++;
				}

				else if (2 == clicks) {
					outputText = mEditText.getText().toString() + "!!!";
					mTextView.setText(outputText);
					mTextView.setTextSize(40);
					clicks++;
				}

				else {
					outputText = mEditText.getText().toString() + "!!!!";
					mTextView.setText(outputText);
					mTextView.setTextSize(50);
					mShoutButton.setClickable(false);
					mShoutButton.setTextColor(Color.GRAY);
				}
			}
		});

		mEraseInputButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mEditText.setText("");
			}
		});

		mStartOverButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mShoutButton.setClickable(true);
				mShoutButton.setTextColor(Color.BLACK);
				mShoutButton.setActivated(true);
				clicks = 0;
				mEditText.setText("");
				mTextView.setText("output");
				mTextView.setTextSize(15);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
