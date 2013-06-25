/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Snake: a simple game that everyone can enjoy.
 * 
 * This is an implementation of the classic Game "Snake", in which you control a
 * serpent roaming around the garden looking for apples. Be careful, though,
 * because when you catch one, not only will you become longer, but you'll move
 * faster. Running into yourself or the walls will end the game.
 * 
 */
public class Snake extends Activity implements OnSharedPreferenceChangeListener {

	private SnakeView mSnakeView;
	public static final String SNAKE = "SNAKE";
	private static String ICICLE_KEY = "snake-view";
	private static final int REQUEST_CODE_SNAKE_PREFS = 1;
	public static final String GAME_IS_PAUSED = "GAME_IS_PAUSED";

	/**
	 * Called when Activity is first created. Turns off the title bar, sets up
	 * the content views, and fires up the SnakeView.
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.snake_layout);

		mSnakeView = (SnakeView) findViewById(R.id.snake);
		mSnakeView.setTextView((TextView) findViewById(R.id.text));

		if (savedInstanceState == null) {
			// We were just launched -- set up a new game
			mSnakeView.setMode(SnakeView.READY);
		} else {
			// We are being restored
			Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
			if (map != null) {
				mSnakeView.restoreState(map);
			} else {
				mSnakeView.setMode(SnakeView.PAUSE);
			}
		}
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mSnakeView.getMode() == SnakeView.RUNNING) {
			mSnakeView.setMode(SnakeView.PAUSE);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Store the game state
		outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.resume:
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.left_turn:
			if (mSnakeView.getDirection() == SnakeView.NORTH) {
				mSnakeView.setNextDirection(SnakeView.WEST);
			} else if (mSnakeView.getDirection() == SnakeView.WEST) {
				mSnakeView.setNextDirection(SnakeView.SOUTH);
			} else if (mSnakeView.getDirection() == SnakeView.SOUTH) {
				mSnakeView.setNextDirection(SnakeView.EAST);
			} else if (mSnakeView.getDirection() == SnakeView.EAST) {
				mSnakeView.setNextDirection(SnakeView.NORTH);
			}
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.right_turn:
			if (mSnakeView.getDirection() == SnakeView.NORTH) {
				mSnakeView.setNextDirection(SnakeView.EAST);
			} else if (mSnakeView.getDirection() == SnakeView.WEST) {
				mSnakeView.setNextDirection(SnakeView.NORTH);
			} else if (mSnakeView.getDirection() == SnakeView.SOUTH) {
				mSnakeView.setNextDirection(SnakeView.WEST);
			} else if (mSnakeView.getDirection() == SnakeView.EAST) {
				mSnakeView.setNextDirection(SnakeView.SOUTH);
			}
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.slow:
			mSnakeView.setModeDelay(600);
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.medium:
			mSnakeView.setModeDelay(300);
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.fast:
			mSnakeView.setModeDelay(100);
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		case R.id.menu_preferences:
			Log.d(SNAKE, "You pressed the menu prefs!");
			Intent snakeIntent = new Intent(this, SnakePreferences.class);
			boolean isPaused = ((mSnakeView.getMode() == SnakeView.RUNNING) || (mSnakeView
					.getMode() == SnakeView.PAUSE));
			snakeIntent.putExtra(GAME_IS_PAUSED, isPaused);
			this.startActivityForResult(snakeIntent, REQUEST_CODE_SNAKE_PREFS);
			break;
		default:
			Log.d(SNAKE, "Unrecognized ID");
			break;
		}

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		if ((mSnakeView.getMode() == SnakeView.LOSE)
				|| (mSnakeView.getMode() == SnakeView.READY)) {
			menu.findItem(R.id.resume).setVisible(false);
			menu.findItem(R.id.left_turn).setVisible(false);
			menu.findItem(R.id.right_turn).setVisible(false);
			menu.findItem(R.id.change_speed).setVisible(false);
		}

		else {
			mSnakeView.setMode(SnakeView.PAUSE);
			menu.findItem(R.id.resume).setVisible(true);
			menu.findItem(R.id.left_turn).setVisible(true);
			menu.findItem(R.id.right_turn).setVisible(true);
			menu.findItem(R.id.change_speed).setVisible(true);
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_SNAKE_PREFS:
			mSnakeView.setMode(SnakeView.RUNNING);
			break;
		default:
			Log.d(SNAKE, "Request code unrecognized!");
			break;
		}
	}

	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		mSnakeView.initSnakeView();
	}
}
