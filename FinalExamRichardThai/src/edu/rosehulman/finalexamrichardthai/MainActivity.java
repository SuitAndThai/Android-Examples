package edu.rosehulman.finalexamrichardthai;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private TextView mTextView;
	private Button mButThisDice;
	private Button mButRoll;
	private Button mButAllDice;
	private Spinner mSpinner;
	private SQLiteDieAdapter mDbScoreAdapter;
	private SimpleCursorAdapter mScoreAdapter;
	private Cursor mScoresCursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.textview_past_rolls);
		mButThisDice = (Button) findViewById(R.id.but_this_dice);
		mButRoll = (Button) findViewById(R.id.but_roll);
		mButAllDice = (Button) findViewById(R.id.but_all_dice);
		mSpinner = (Spinner) findViewById(R.id.spinner);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
