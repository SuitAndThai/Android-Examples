package edu.rosehulman.hellodialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HelloDialogs extends FragmentActivity {

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

		((Button) findViewById(R.id.dialog_button_ok))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with one button to info user.");
						// showDialog(DIALOG_ID_ABOUT);
						DialogFragment df = new DialogFragment() {
							@Override
							public Dialog onCreateDialog(
									Bundle savedInstanceState) {
								AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(
										HelloDialogs.this);
								aboutDialogBuilder
										.setMessage(R.string.about_description);
								aboutDialogBuilder
										.setIcon(R.drawable.rosielogo);
								aboutDialogBuilder
										.setTitle(R.string.about_dialogs);
								aboutDialogBuilder.setCancelable(true);
								aboutDialogBuilder.setPositiveButton(
										R.string.ok,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialogInt,
													int which) {
												dialogInt.dismiss();
											}
										});
								return aboutDialogBuilder.create();
							}
						};

						df.show(getSupportFragmentManager(), "");
					}
				});
		((Button) findViewById(R.id.dialog_button_multiple_buttons))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with multiple options for the user.");
						showDialog(DIALOG_ID_EXIT);
					}
				});
		((Button) findViewById(R.id.dialog_button_select_item))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.d(HD,
								"Show alert dialog with a list of options for the user.");
						showDialog(DIALOG_ID_LEARNING_SURVEY);
					}
				});
		((Button) findViewById(R.id.dialog_button_progress))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.d(HD, "Show a progress dialog.");
						showDialog(DIALOG_ID_LOADING);
					}
				});
		((Button) findViewById(R.id.dialog_button_custom))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.d(HD, "Show custom dialog subclass to the user.");
						showDialog(DIALOG_ID_ROSE_HULMAN);
					}
				});
	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID_ABOUT:
			AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(
					this);
			aboutDialogBuilder.setMessage(R.string.about_description);
			aboutDialogBuilder.setIcon(R.drawable.rosielogo);
			aboutDialogBuilder.setTitle(R.string.about_dialogs);
			aboutDialogBuilder.setCancelable(true);
			aboutDialogBuilder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInt, int which) {
							dialogInt.dismiss();
						}
					});
			dialog = aboutDialogBuilder.create();
			break;
		case DIALOG_ID_EXIT:
			AlertDialog.Builder exitDialogBuilder = new AlertDialog.Builder(
					this);
			exitDialogBuilder
					.setMessage(
							"Would you like to exit or get a Toast message?")
					.setCancelable(false)
					.setPositiveButton("Toast",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Toast.makeText(HelloDialogs.this,
											"Thank you for not exiting",
											Toast.LENGTH_SHORT).show();
									dialog.dismiss();
								}
							})
					.setNeutralButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})
					.setNegativeButton("Exit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							});
			dialog = exitDialogBuilder.create();
			break;
		case DIALOG_ID_LEARNING_SURVEY:
			AlertDialog.Builder learningSurveyBuilder = new AlertDialog.Builder(
					this);
			learningSurveyBuilder.setCancelable(true)
					.setTitle(R.string.learning_methods_question)
					// radio buttons
					// .setSingleChoiceItems(R.array.learning_methods_array, 2,

					// simple list
					// .setItems(R.array.learning_methods_array,

					// multiple choice boxes
					.setMultiChoiceItems(R.array.learning_methods_array,
							new boolean[] { true, true, true, false, true },
							new DialogInterface.OnMultiChoiceClickListener() {

								// new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
								// int which) {
										int which, boolean isChecked) {
									Resources res = getResources();
									String[] items = res
											.getStringArray(R.array.learning_methods_array);
									if (isChecked) {
										Toast.makeText(HelloDialogs.this,
												"Yeah for " + items[which],
												Toast.LENGTH_SHORT).show();
									} else {
										Toast.makeText(getApplicationContext(),
												"Too bad for " + items[which],
												Toast.LENGTH_SHORT).show();
									}
									dialog.dismiss();
								}
							});
			learningSurveyBuilder.setNeutralButton(R.string.done, null);
			dialog = learningSurveyBuilder.create();
			break;
		case DIALOG_ID_LOADING:
			ProgressDialog loadingDialog = new ProgressDialog(this);

			// loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			loadingDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			loadingDialog.incrementProgressBy(55);

			loadingDialog.setMessage("Loading...");
			loadingDialog.setCancelable(true);
			dialog = loadingDialog;
			break;
		case DIALOG_ID_ROSE_HULMAN:
			final Dialog roseDialog = new Dialog(this);
			roseDialog.setContentView(R.layout.custom_dialog);
			roseDialog.setTitle(R.string.rose_hulman);

			Button doneButton = (Button) roseDialog
					.findViewById(R.id.done_button);

			doneButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					roseDialog.dismiss();
				}
			});
			dialog = roseDialog;
			break;
		default:
			break;
		}
		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);

		switch (id) {
		case DIALOG_ID_LOADING:
			((ProgressDialog) dialog).incrementProgressBy(1);
			break;
		}
	}
}