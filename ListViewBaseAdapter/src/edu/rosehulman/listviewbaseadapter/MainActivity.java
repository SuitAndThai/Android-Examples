package edu.rosehulman.listviewbaseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ListView myListView = (ListView) findViewById(R.id.list_view);
		final Button addViewButton = (Button) findViewById(R.id.add_view_button);
		final RowNumberAdapter rowNumberAdapter = new RowNumberAdapter(this);

		// String[] names = { "Richard", "Susi", "Mark", "Andrew" };
		// ArrayAdapter adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, names);
		myListView.setAdapter(rowNumberAdapter);

		addViewButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// add a view
				rowNumberAdapter.addView();
				// notify adapter that the d ata changed
				rowNumberAdapter.notifyDataSetChanged();
			}
		});

		myListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// When clicked, show a toast with the TextView text
						Toast.makeText(
								getApplicationContext(),
								"You pressed "
										+ rowNumberAdapter.getItem(position),
								Toast.LENGTH_SHORT).show();
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
