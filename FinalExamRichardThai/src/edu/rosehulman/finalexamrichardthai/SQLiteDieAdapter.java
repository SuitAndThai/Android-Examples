package edu.rosehulman.finalexamrichardthai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDieAdapter {
	private static final String TAG = "SQLiteDieAdapter"; // Just the tag we
	// use to log

	private static final String DATABASE_NAME = "dies.db"; // Becomes the
	// filename of
	// the database
	private static final String TABLE_NAME = "dies"; // Only one table in this
	// database
	private static final int DATABASE_VERSION = 1; // We increment this every
	// time we change the
	// database schema
	// which will kick off an
	// automatic upgrade

	private SQLiteOpenHelper mOpenHelper;
	private SQLiteDatabase mDb;

	public static final String KEY_ID = "_id";
	public static final String KEY_NUM_SIDES = "num_sides";
	public static final String KEY_VALUE_ROLLED = "value_rolled";

	private static final int COLUMN_INDEX_ID = 0;
	private static final int COLUMN_NUM_SIDES = 1;
	private static final int COLUMN_VALUE_ROLLED = 2;

	private static String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private static String CREATE_STATEMENT;
	static {
		StringBuilder s = new StringBuilder();
		s.append("CREATE TABLE " + TABLE_NAME + " (");
		s.append(KEY_ID + " integer primary key autoincrement, ");
		s.append(KEY_NUM_SIDES + " integer, ");
		s.append(KEY_VALUE_ROLLED + " integer");
		s.append(")");
		CREATE_STATEMENT = s.toString();
	}

	private static class DieDbHelper extends SQLiteOpenHelper {
		public DieDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_STATEMENT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d(TAG, "Updating from version " + oldVersion + " to "
					+ newVersion + ", which will destroy old table(s).");
			db.execSQL(DROP_STATEMENT);
			onCreate(db);
		}
	}

	public Cursor getDiesCursor() {
		String[] projection = new String[] { KEY_ID, KEY_NUM_SIDES,
				KEY_VALUE_ROLLED };
		return mDb.query(TABLE_NAME, projection, null, null, null, null,
				KEY_VALUE_ROLLED + " DESC");
	}

	public Die getDie(long id) {
		// Getting a cursor for the specific id
		String[] projection = new String[] { KEY_ID, KEY_NUM_SIDES,
				KEY_VALUE_ROLLED };
		String selection = KEY_ID + "=" + id;
		Cursor c = mDb.query(true, TABLE_NAME, projection, selection, null,
				null, null, null, null);
		// Using the cursor to get values from the table
		if (c != null && c.moveToFirst()) {
			Die d = new Die();
			d.setID(c.getInt(COLUMN_INDEX_ID)); // Same value as id parameter
			d.setName(c.getString(COLUMN_NUM_SIDES));
			d.setDie(c.getInt(COLUMN_INDEX_DIE));
			return d;
		}
		return null;
	}

	/**
	 * Add die to the table. If the die is successfully added return the new id
	 * for that die, otherwise return a -1 to indicate failure.
	 * 
	 * @param die
	 * @return id of the inserted row or -1 if failed
	 */
	public long addDie(Die die) {
		ContentValues rowValues = getContentValuesFromDie(die);
		return mDb.insert(TABLE_NAME, null, rowValues);
	}

	public void updateDie(Die die) {
		ContentValues rowValues = getContentValuesFromDie(die);
		String whereClause = KEY_ID + "=" + die.getID();
		mDb.update(TABLE_NAME, rowValues, whereClause, null);
	}

	public boolean removeDie(long id) {
		return mDb.delete(TABLE_NAME, KEY_ID + "=" + id, null) > 0;
	}

	public boolean removeDie(Die d) {
		return removeDie(d.getID());
	}

	private ContentValues getContentValuesFromDie(Die die) {
		ContentValues rowValues = new ContentValues();
		rowValues.put(KEY_NUM_SIDES, die.getNumSides());
		rowValues.put(KEY_VALUE_ROLLED, die.getRoll());
		return rowValues;
	}

	public SQLiteDieAdapter(Context context) {
		mOpenHelper = new DieDbHelper(context);
	}

	public void open() {
		mDb = mOpenHelper.getWritableDatabase();
	}

	public void close() {
		mDb.close();
	}
}
