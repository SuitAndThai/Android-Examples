package edu.rosehulman.hellodialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HelloDialogs extends Activity {

	public static final String HD = "HD";
	private static final int DIALOG_ID_ABOUT = 0;
	private static final int DIALOG_ID_EXIT = 1;
	private static final int DIALOG_ID_LEARNING_SURVEY = 2;
	private static final int DIALOG_ID_LOADING = 3;
	private static final int DIALOG_ID_ROSE_HULMAN = 4;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		((Button) findViewById(R.id.dialog_button_ok)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(HD, "Show alert dialog with one button to info user.");
				// TODO: Show appropriate dialog
			}
		});
		((Button) findViewById(R.id.dialog_button_multiple_buttons)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(HD, "Show alert dialog with multiple options for the user.");
				// TODO: Show appropriate dialog
			}
		});
		((Button) findViewById(R.id.dialog_button_select_item)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(HD, "Show alert dialog with a list of options for the user.");
				// TODO: Show appropriate dialog
			}
		});
		((Button) findViewById(R.id.dialog_button_progress)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(HD, "Show a progress dialog.");
				// TODO: Show appropriate dialog
			}
		});
		((Button) findViewById(R.id.dialog_button_custom)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(HD, "Show custom dialog subclass to the user.");
				// TODO: Show appropriate dialog
			}
		});
	}
}