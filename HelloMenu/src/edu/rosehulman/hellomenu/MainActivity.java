package edu.rosehulman.hellomenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String HM = "HM";
	private FrameLayout mLayout;
	private TextView mMovieQuoteTestView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mLayout = (FrameLayout) findViewById(R.id.frame_layout);
		mMovieQuoteTestView = (TextView) findViewById(R.id.movie_quote_text_view);

		registerForContextMenu(this.mMovieQuoteTestView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater contextMenuInflater = getMenuInflater();
		contextMenuInflater.inflate(R.menu.context_menu, menu);

		menu.setHeaderTitle(getString(R.string.select_quote));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);
		String msg = item.getTitle().toString();
		this.mMovieQuoteTestView.setText(msg);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		FrameLayout.LayoutParams frameLayoutParams = (FrameLayout.LayoutParams) this.mMovieQuoteTestView
				.getLayoutParams();

		switch (item.getItemId()) {
		case R.id.yellow_background:
			Log.d(HM, "Yellow!");
			this.mLayout.setBackgroundColor(Color.YELLOW);
			return true;
		case R.id.white_background:
			Log.d(HM, "White!");
			this.mLayout.setBackgroundColor(Color.WHITE);
			return true;
		case R.id.blue_background:
			Log.d(HM, "Blue!");
			this.mLayout.setBackgroundColor(Color.BLUE);
			return true;

		case R.id.red_text_color:
			this.mMovieQuoteTestView.setTextColor(Color.RED);
			return true;
		case R.id.white_text_color:
			this.mMovieQuoteTestView.setTextColor(Color.WHITE);
			return true;
		case R.id.black_text_color:
			this.mMovieQuoteTestView.setTextColor(Color.BLACK);
			return true;
		case R.id.green_text_color:
			this.mMovieQuoteTestView.setTextColor(Color.GREEN);
			return true;

		case R.id.left:
			if (item.isChecked()) {
				frameLayoutParams.gravity |= Gravity.LEFT;
			} else {
				frameLayoutParams.gravity &= ~Gravity.LEFT;
				frameLayoutParams.gravity |= Gravity.CENTER_HORIZONTAL;
			}
			this.mMovieQuoteTestView.setLayoutParams(frameLayoutParams);
			return true;
		case R.id.top:
			if (item.isChecked()) {
				frameLayoutParams.gravity |= Gravity.TOP;
			} else {
				frameLayoutParams.gravity &= ~Gravity.TOP;
				frameLayoutParams.gravity |= Gravity.CENTER_VERTICAL;
			}
			this.mMovieQuoteTestView.setLayoutParams(frameLayoutParams);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
