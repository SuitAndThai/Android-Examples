package edu.rosehulman.blackjacktrainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import edu.rosehulman.blackjacktrainer.Card.CardName;
import edu.rosehulman.blackjacktrainer.Card.Suit;

public class Blackjack extends Activity {

	public static final String BLACKJACK = "BLACKJACK";
	public static final int DIALOG_ID_MAKE_YOUR_MOVE = 0;
	public static final int DIALOG_ID_SKIP = 1;
	public static final int DIALOG_ID_CHEAT = 2;
	public static final int DIALOG_ID_COACH_COMMENT = 3;
	public static final int DIALOG_ID_ABOUT = 4;
	public static final String BJ = "BJ";

	private static final int[] DRAWABLES_CLUBS = { 0,
			R.drawable.playing_card_club_a, R.drawable.playing_card_club_2,
			R.drawable.playing_card_club_3, R.drawable.playing_card_club_4,
			R.drawable.playing_card_club_5, R.drawable.playing_card_club_6,
			R.drawable.playing_card_club_7, R.drawable.playing_card_club_8,
			R.drawable.playing_card_club_9, R.drawable.playing_card_club_10,
			R.drawable.playing_card_club_j, R.drawable.playing_card_club_q,
			R.drawable.playing_card_club_k };
	private static final int[] DRAWABLES_DIAMONDS = { 0,
			R.drawable.playing_card_diamond_a,
			R.drawable.playing_card_diamond_2,
			R.drawable.playing_card_diamond_3,
			R.drawable.playing_card_diamond_4,
			R.drawable.playing_card_diamond_5,
			R.drawable.playing_card_diamond_6,
			R.drawable.playing_card_diamond_7,
			R.drawable.playing_card_diamond_8,
			R.drawable.playing_card_diamond_9,
			R.drawable.playing_card_diamond_10,
			R.drawable.playing_card_diamond_j,
			R.drawable.playing_card_diamond_q,
			R.drawable.playing_card_diamond_k };
	private static final int[] DRAWABLES_HEARTS = { 0,
			R.drawable.playing_card_heart_a, R.drawable.playing_card_heart_2,
			R.drawable.playing_card_heart_3, R.drawable.playing_card_heart_4,
			R.drawable.playing_card_heart_5, R.drawable.playing_card_heart_6,
			R.drawable.playing_card_heart_7, R.drawable.playing_card_heart_8,
			R.drawable.playing_card_heart_9, R.drawable.playing_card_heart_10,
			R.drawable.playing_card_heart_j, R.drawable.playing_card_heart_q,
			R.drawable.playing_card_heart_k };
	private static final int[] DRAWABLES_SPADES = { 0,
			R.drawable.playing_card_spade_a, R.drawable.playing_card_spade_2,
			R.drawable.playing_card_spade_3, R.drawable.playing_card_spade_4,
			R.drawable.playing_card_spade_5, R.drawable.playing_card_spade_6,
			R.drawable.playing_card_spade_7, R.drawable.playing_card_spade_8,
			R.drawable.playing_card_spade_9, R.drawable.playing_card_spade_10,
			R.drawable.playing_card_spade_j, R.drawable.playing_card_spade_q,
			R.drawable.playing_card_spade_k };
	private ImageView mDealerCardImageView;
	private ImageView mPlayerCardImageView_1;
	private ImageView mPlayerCardImageView_2;

	private BlackjackRound mRound;
	private String mActionSelected;
	private static final String KEY_DEALER_CARD = "KEY_DEALER_CARD";
	private static final String KEY_PLAYER_CARD_1 = "KEY_PLAYER_CARD_1";
	private static final String KEY_PLAYER_CARD_2 = "KEY_PLAYER_CARD_2";
	private static final String KEY_NUM_CORRECT = "KEY_NUM_CORRECT";
	private static final String KEY_NUM_ROUNDS = "KEY_NUM_ROUNDS";
	private static final String KEY_ACTION_SELECTED = "KEY_ACTION_SELECTED";
	private int mNumCorrect;
	private int mNumRounds;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.mDealerCardImageView = (ImageView) findViewById(R.id.dealer_card_1);
		this.mPlayerCardImageView_1 = (ImageView) findViewById(R.id.player_card_1);
		this.mPlayerCardImageView_2 = (ImageView) findViewById(R.id.player_card_2);

		((Button) findViewById(R.id.make_your_move_button))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						showDialog(DIALOG_ID_MAKE_YOUR_MOVE);
					}
				});
		((Button) findViewById(R.id.skip_button))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						showDialog(DIALOG_ID_SKIP);
					}
				});
		((Button) findViewById(R.id.cheat_button))
				.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						showDialog(DIALOG_ID_CHEAT);
					}
				});

		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		this.mNumCorrect = prefs.getInt(KEY_NUM_CORRECT, 0);
		this.mNumRounds = prefs.getInt(KEY_NUM_ROUNDS, 0);
		String dealerCardString = prefs.getString(KEY_DEALER_CARD, "");
		String playerCardString1 = prefs.getString(KEY_PLAYER_CARD_1, "");
		String playerCardString2 = prefs.getString(KEY_PLAYER_CARD_2, "");
		if (dealerCardString.equalsIgnoreCase("")
				|| playerCardString1.equalsIgnoreCase("")
				|| playerCardString2.equalsIgnoreCase("")) {
			initNewRound();
		} else {
			Card dealerCard = new Card(dealerCardString);
			Card playerCard1 = new Card(playerCardString1);
			Card playerCard2 = new Card(playerCardString2);
			this.mRound = new BlackjackRound(this, dealerCard, playerCard1,
					playerCard2);
			updateView();
			this.mActionSelected = prefs.getString(KEY_ACTION_SELECTED, ""); // Fixing
																				// a
																				// bug
																				// when
																				// the
																				// coach
																				// dialog
																				// is
																				// open
																				// during
																				// a
																				// rotate
		}
	}

	private void initNewRound() {
		Blackjack.this.mRound = new BlackjackRound(this);
		updateView();
	}

	private void updateView() {
		Card.CardName dealerCardName = this.mRound.getDealerCard()
				.getCardName();
		Card.Suit dealerSuit = this.mRound.getDealerCard().getSuit();
		Card.CardName playerCardName1 = this.mRound.getPlayerCard1()
				.getCardName();
		Card.Suit playerSuit1 = this.mRound.getPlayerCard1().getSuit();
		Card.CardName playerCardName2 = this.mRound.getPlayerCard2()
				.getCardName();
		Card.Suit playerSuit2 = this.mRound.getPlayerCard2().getSuit();

		int dealerCardDrawableIndex = getDrawableIndexFromCardName(dealerCardName);
		int playerCardDrawableIndex1 = getDrawableIndexFromCardName(playerCardName1);
		int playerCardDrawableIndex2 = getDrawableIndexFromCardName(playerCardName2);

		int[] dealerDrawableArray = getDrawableArrayFromSuit(dealerSuit);
		int[] playerDrawableArray1 = getDrawableArrayFromSuit(playerSuit1);
		int[] playerDrawableArray2 = getDrawableArrayFromSuit(playerSuit2);

		this.mDealerCardImageView
				.setImageResource(dealerDrawableArray[dealerCardDrawableIndex]);
		this.mPlayerCardImageView_1
				.setImageResource(playerDrawableArray1[playerCardDrawableIndex1]);
		this.mPlayerCardImageView_2
				.setImageResource(playerDrawableArray2[playerCardDrawableIndex2]);
	}

	private int[] getDrawableArrayFromSuit(Suit dealerSuit) {
		switch (dealerSuit) {
		case CLUBS:
			return DRAWABLES_CLUBS;
		case DIAMONDS:
			return DRAWABLES_DIAMONDS;
		case HEARTS:
			return DRAWABLES_HEARTS;
		case SPADES:
			return DRAWABLES_SPADES;
		default:
			return null;
		}
	}

	private int getDrawableIndexFromCardName(CardName cardName) {
		switch (cardName) {
		case KING:
			return 13;
		case QUEEN:
			return 12;
		case JACK:
			return 11;
		default:
			return Card.numericValueFromCardName(cardName);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(KEY_NUM_CORRECT, this.mNumCorrect);
		editor.putInt(KEY_NUM_ROUNDS, this.mNumRounds);
		editor.putString(KEY_DEALER_CARD, this.mRound.getDealerCard()
				.toString());
		editor.putString(KEY_PLAYER_CARD_1, this.mRound.getPlayerCard1()
				.toString());
		editor.putString(KEY_PLAYER_CARD_2, this.mRound.getPlayerCard2()
				.toString());
		editor.putString(KEY_ACTION_SELECTED, this.mActionSelected);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about_menu_item:
			showDialog(DIALOG_ID_ABOUT);
			return true;
		case R.id.reset_menu_item:
			this.mNumCorrect = 0;
			this.mNumRounds = 0;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;

		switch (id) {
		case DIALOG_ID_ABOUT:
			Log.d(BJ, "about dialog pressed");
			AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(
					this);
			aboutDialogBuilder.setMessage(R.string.about_message);
			aboutDialogBuilder.setIcon(R.drawable.ic_blackjack);
			aboutDialogBuilder.setTitle(R.string.about_title);
			aboutDialogBuilder.setCancelable(true);
			aboutDialogBuilder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInt, int which) {
							dialogInt.dismiss();
						}
					});
			dialog = aboutDialogBuilder.create();
			break;
		case DIALOG_ID_SKIP:
			Log.d(BJ, "skip dialog pressed");
			AlertDialog.Builder skipDialogBuilder = new AlertDialog.Builder(
					this);
			skipDialogBuilder.setMessage(R.string.skip_message);
			skipDialogBuilder.setIcon(R.drawable.ic_blackjack);
			skipDialogBuilder.setTitle(R.string.skip);
			skipDialogBuilder.setCancelable(true);
			skipDialogBuilder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInt, int which) {
							dialogInt.dismiss();
							initNewRound();
						}
					});
			skipDialogBuilder.setNeutralButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInt, int which) {
							dialogInt.dismiss();
						}
					});
			dialog = skipDialogBuilder.create();
			break;
		case DIALOG_ID_CHEAT:
			Log.d(BJ, "cheat dialog pressed");
			Dialog cheatDialogBuilder = new Dialog(this);
			cheatDialogBuilder.setContentView(R.layout.cheat_matrix_layout);
			cheatDialogBuilder.setTitle(getString(R.string.coach_advice,
					this.mRound.toString(),
					this.mRound.getCorrectPlayerActionAsString()));
			cheatDialogBuilder.setCancelable(true);

			dialog = cheatDialogBuilder;

			break;
		case DIALOG_ID_MAKE_YOUR_MOVE:
			Log.d(BJ, "make your move dialog pressed");
			AlertDialog.Builder makeYourMoveDialogBuilder = new AlertDialog.Builder(
					this);
			makeYourMoveDialogBuilder.setItems(
					this.mRound.legalMovesAsString(),
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							mActionSelected = mRound.legalMovesAsString()[which];
							dialog.dismiss();
							removeDialog(DIALOG_ID_MAKE_YOUR_MOVE);
							showDialog(DIALOG_ID_COACH_COMMENT);
						}
					});
			makeYourMoveDialogBuilder.setTitle(R.string.make_your_move_title);
			makeYourMoveDialogBuilder.setCancelable(true);

			dialog = makeYourMoveDialogBuilder.create();
			break;
		case DIALOG_ID_COACH_COMMENT:
			Log.d(BJ, "coach dialog pops up now");
			AlertDialog.Builder coachDialogBuilder = new AlertDialog.Builder(
					this);
			mNumRounds++;
			if (mActionSelected == mRound.getCorrectPlayerActionAsString()) {
				coachDialogBuilder.setTitle(R.string.correct);
				coachDialogBuilder.setIcon(R.drawable.correct_move);
				mNumCorrect++;
				coachDialogBuilder.setMessage(getString(
						R.string.percentage_correct, mNumCorrect, mNumRounds,
						(double) mNumCorrect / mNumRounds * 100.0));
			} else {
				coachDialogBuilder.setTitle(R.string.incorrect);
				coachDialogBuilder.setIcon(R.drawable.incorrect_move);
				String message = getString(R.string.coach_advice,
						this.mRound.toString(),
						this.mRound.getCorrectPlayerActionAsString());
				message += getString(R.string.percentage_correct, mNumCorrect,
						mNumRounds, (double) mNumCorrect / mNumRounds * 100.0);
				coachDialogBuilder.setMessage(message);
			}
			coachDialogBuilder.setCancelable(true);
			coachDialogBuilder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInt, int which) {
							dialogInt.dismiss();
							removeDialog(DIALOG_ID_COACH_COMMENT);
							initNewRound();
						}
					});

			dialog = coachDialogBuilder.create();
			break;
		default:
			Log.d(BJ, "unknown dialog pressed");
			break;
		}

		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_ID_CHEAT:
			Log.d(BJ, "prepping the cheat dialog");
			dialog.setTitle(getString(R.string.coach_advice,
					this.mRound.toString(),
					this.mRound.getCorrectPlayerActionAsString()));

			break;
		default:
			Log.d(BJ, "no need to prep for this dialog");
			break;
		}
	}
}