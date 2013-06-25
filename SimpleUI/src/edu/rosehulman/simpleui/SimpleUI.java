package edu.rosehulman.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SimpleUI extends Activity {

	private Button button;
	private EditText editText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_ui);
        
        button = (Button) findViewById(R.id.button_send);
        editText = (EditText) findViewById(R.id.edit_message);
        
        button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

	        	if (editText.getText().toString().equals(getString(R.string.secret))) {
	        		button.setText(R.string.button_text_wow);
	        		} else {
	        		button.setText(R.string.button_send);
	        		}
	        				
			}});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_simple_ui, menu);
		return true;
	}
}
