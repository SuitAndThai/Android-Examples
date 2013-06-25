package edu.rosehulman.rosehomework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import Model.Task;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayTasksActivity extends Activity {

	private ListView mTaskListView;
	private List<Task> mTasks;
	private ArrayAdapter<Task> mTaskAdapter;
	private HomeworkDbAdapter mDbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_tasks);

		mTaskListView = (ListView) findViewById(R.id.display_tasks_task_list);
		mTasks = new ArrayList<Task>();

		// Dummy values
		// Task task1 = new Task("Math homework");
		// Task task2 = new Task("Android homework");
		// task1.setDateDue(new GregorianCalendar());
		// task2.setDateDue(new GregorianCalendar());
		// task1.setCourse("DE I");
		// task2.setCourse("Physics");
		//
		// mTasks.add(task1);
		// mTasks.add(task2);

		mDbAdapter = new HomeworkDbAdapter(this);
		mDbAdapter.open();
		mTasks = mDbAdapter.getAllTasks();
		Collections.sort(mTasks);

		mTaskAdapter = new ArrayAdapter<Task>(this,
				android.R.layout.simple_list_item_1, mTasks);
		mTaskListView.setAdapter(mTaskAdapter);

		mTaskListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				deleteTask(mTasks.get(position));
				return false;
			}
		});
		// updateFromDb();
	}

	private void updateFromDb() {
		mTasks.clear();
		mTasks.addAll(mDbAdapter.getAllTasks());
		// mTasks = mDbAdapter.getAllTasks();
		Collections.sort(mTasks);
		mTaskAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDbAdapter.close();
	}

	// private void updateList() {
	// List<Task> temp = (ArrayList) mDbAdapter.getAllTasks();
	// mTasks = Collections.sort(temp);
	// }

	private void addTask(Task task) {
		Toast.makeText(this, "Adding task: " + task, Toast.LENGTH_SHORT).show();
		task = mDbAdapter.addTask(task);
		mTasks.add(task);
		Collections.sort(mTasks);
		mTaskAdapter.notifyDataSetChanged();
	}

	private void deleteTask(Task task) {
		Toast.makeText(this, "Deleting task: " + task, Toast.LENGTH_SHORT)
				.show();
		mDbAdapter.deleteTask(task);
		mTasks.remove(task);
		mTaskAdapter.notifyDataSetChanged();
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.create_dialog);
		dialog.setTitle(R.string.add_task);

		final EditText nameText = (EditText) dialog
				.findViewById(R.id.create_dialog_name);
		final EditText courseText = (EditText) dialog
				.findViewById(R.id.create_dialog_course);
		final DatePicker datePicker = (DatePicker) dialog
				.findViewById(R.id.create_dialog_date);
		final Button addButton = (Button) dialog
				.findViewById(R.id.create_dialog_and_add_button);
		final Button cancelButton = (Button) dialog
				.findViewById(R.id.create_dialog_and_cancel_button);

		addButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Task task = new Task();
				task.setName(nameText.getText().toString());
				task.setDateDue(datePicker.getYear(), datePicker.getMonth(),
						datePicker.getDayOfMonth());
				task.setCourse(courseText.getText().toString());
				addTask(task);
				nameText.setText("");
				courseText.setText("");
				dialog.dismiss();
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		return dialog;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		if (item.getItemId() == R.id.menu_add_task) {
			showDialog(0);
		}

		return false;
	}
}
