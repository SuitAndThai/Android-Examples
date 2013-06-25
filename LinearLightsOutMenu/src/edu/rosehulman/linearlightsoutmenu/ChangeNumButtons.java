package edu.rosehulman.linearlightsoutmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeNumButtons extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_num_buttons);

		Intent data = this.getIntent();
		int nButtons = data.getIntExtra(MainMenuActivity.KEY_NUM_BUTTONS, 7);
		Log.d(MainMenuActivity.LLOM, "Got " + nButtons + " buttons.");

		// Use to set which radio button is checked.
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		switch (nButtons) {
		case 3:
			radioGroup.check(R.id.radio_3);
			break;
		case 5:
			radioGroup.check(R.id.radio_5);
			break;
		case 7:
			radioGroup.check(R.id.radio_7);
			break;
		case 9:
			radioGroup.check(R.id.radio_9);
			break;
		default:
			// do nothing
			break;
		}

		((RadioButton) findViewById(R.id.radio_3)).setOnClickListener(this);
		((RadioButton) findViewById(R.id.radio_5)).setOnClickListener(this);
		((RadioButton) findViewById(R.id.radio_7)).setOnClickListener(this);
		((RadioButton) findViewById(R.id.radio_9)).setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent result = new Intent();

		switch (v.getId()) {
		case R.id.radio_3:
			Log.d(MainMenuActivity.LLOM, "You clicked radio button 3");
			result.putExtra(MainMenuActivity.KEY_NUM_BUTTONS, 3);
			break;
		case R.id.radio_5:
			Log.d(MainMenuActivity.LLOM, "You clicked radio button 5");
			result.putExtra(MainMenuActivity.KEY_NUM_BUTTONS, 5);
			break;
		case R.id.radio_7:
			Log.d(MainMenuActivity.LLOM, "You clicked radio button 7");
			result.putExtra(MainMenuActivity.KEY_NUM_BUTTONS, 7);
			break;
		case R.id.radio_9:
			Log.d(MainMenuActivity.LLOM, "You clicked radio button 9");
			result.putExtra(MainMenuActivity.KEY_NUM_BUTTONS, 9);
			break;
		default:
			// Nothing
		}

		this.setResult(RESULT_OK, result);
		this.finish();
	}
}
