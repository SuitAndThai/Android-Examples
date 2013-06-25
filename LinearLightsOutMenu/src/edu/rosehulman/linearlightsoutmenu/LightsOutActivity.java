package edu.rosehulman.linearlightsoutmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LightsOutActivity extends Activity implements OnClickListener {

	private int mNumButtons;
	private LightsOutGame mGame;
	private ArrayList<Button> mButtons;
	private TextView mGameStateTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lights_out);

		Intent intent = getIntent();
		mNumButtons = intent.getIntExtra(MainMenuActivity.KEY_NUM_BUTTONS, 7);
		mGame = new LightsOutGame(mNumButtons);
		mGameStateTextView = (TextView) findViewById(R.id.game_state);

		// make a new TableRow and an ArrayList
		TableRow buttonRow = new TableRow(this);
		mButtons = new ArrayList<Button>();

		// Create that many buttons in the table and array list
		for (int i = 0; i < mNumButtons; i++) {
			Button currentButton = new Button(this);
			currentButton.setTag(Integer.valueOf(i));
			mButtons.add(currentButton);
			buttonRow.addView(currentButton);
			currentButton.setOnClickListener(this);
		}

		TableLayout buttonTable = (TableLayout) findViewById(R.id.button_table);
		buttonTable.addView(buttonRow);

		// Add listeners

		// Add the row to the layout

		Button newGame = (Button) findViewById(R.id.new_game_button);
		newGame.setOnClickListener(this);
		updateView(false);
	}

	private void updateView(boolean isWin) {
		for (int i = 0; i < mNumButtons; i++) {
			mButtons.get(i).setText("" + mGame.getValueAtIndex(i));
			mButtons.get(i).setEnabled(!isWin);
		}
	}

	public void onClick(View v) {

		boolean isWin = false;

		if (v.getId() == R.id.new_game_button) {
			Log.d(MainMenuActivity.LLOM, "New game button pressed");
			mGame = new LightsOutGame(this.mNumButtons);
		} else {
			Log.d(MainMenuActivity.LLOM, "Button with tag " + v.getTag());
			isWin = mGame.pressedButtonAtIndex((Integer) v.getTag());
		}

		updateView(isWin);

		Resources r = getResources();
		String newGameString;
		int numPresses = mGame.getNumPresses();

		if (isWin) {
			if (1 == numPresses) {
				newGameString = r.getString(R.string.you_won_one_move);
			} else {
				newGameString = r.getString(R.string.you_won_format_string,
						numPresses);
			}
		} else {
			if (0 == numPresses) {
				newGameString = r.getString(R.string.game_start);
			} else if (1 == numPresses) {
				newGameString = r.getString(R.string.game_one_move);
			} else {
				newGameString = r.getString(R.string.game_format, numPresses);
			}
		}

		mGameStateTextView.setText(newGameString);
	}
}
