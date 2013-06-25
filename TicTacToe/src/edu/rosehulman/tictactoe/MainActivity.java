package edu.rosehulman.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private Button[][] mTTTButtons;
	private TextView mGameStateTextView;
	private Button mNewGameButton;
	private TicTacToeGame mGame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tic_tac_toe);

		mTTTButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];

		mTTTButtons[0][0] = (Button) findViewById(R.id.button00);
		mTTTButtons[0][1] = (Button) findViewById(R.id.button01);
		mTTTButtons[0][2] = (Button) findViewById(R.id.button02);
		mTTTButtons[1][0] = (Button) findViewById(R.id.button10);
		mTTTButtons[1][1] = (Button) findViewById(R.id.button11);
		mTTTButtons[1][2] = (Button) findViewById(R.id.button12);
		mTTTButtons[2][0] = (Button) findViewById(R.id.button20);
		mTTTButtons[2][1] = (Button) findViewById(R.id.button21);
		mTTTButtons[2][2] = (Button) findViewById(R.id.button22);

		for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
			for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
				mTTTButtons[row][col].setOnClickListener(this);
			}
		}

		mNewGameButton = (Button) findViewById(R.id.new_game_button);
		mGame = new TicTacToeGame(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_tic_tac_toe, menu);
		return true;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}