package edu.rosehulman.listviewarrayadapter;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText editText = (EditText) findViewById(R.id.edit_text);
		final ListView listView = (ListView) findViewById(R.id.list_view);

		// Make an array
		final StarTextAdapter listValues = new StarTextAdapter(this,
				R.layout.row_view, R.id.right_text_view, listValues);

		listValues.add(new StarText("Array value 1"));
		listValues.add(new StarText("Array value 2"));
		listValues.add(new StarText("Array value 3"));
		// Make the array adapter
		final ArrayAdapter<StarText> arrayAdapter = new ArrayAdapter<StarText>(
		// this, android.R.layout.simple_list_item_multiple_choice, listValues);
		// this, android.R.layout.simple_list_item_single_choice, listValues);
		// this, android.R.layout.simple_list_item_1, listValues);
		// this, R.layout.my_text_view, listValues);
				this, R.layout.row_view, R.id.right_text_view, listValues);

		listView.setAdapter(arrayAdapter);

		editText.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER
							|| keyCode == KeyEvent.KEYCODE_ENTER) {
						// add the text in the edittext to end of the array and
						// clear the edit text
						listValues.add(editText.getText().toString());
						// don't forget to notify teh adapter that the dataset
						// has changed!
						arrayAdapter.notifyDataSetChanged();
						editText.setText("");
						return true;
					}
				}
				return false;
			}
		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				listValues.get(position).addStar();
				while (position != 0
						&& listValues.get(position - 1).getNumStars() < listValues
								.get(position).getNumStars()) {
					Collections.swap(listValues, position, position - 1);
					position--;
				}
				arrayAdapter.notifyDataSetChanged();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
