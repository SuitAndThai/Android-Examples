package edu.rosehulman.finalexamrichardthai;

import java.util.Random;

public class Die {
	private int mNumSides;
	private int mValueRolled;
	private Random random;
	private int mID;

	public int getID() {
		return mID;
	}

	public void setID(int id) {
		mID = id;
	}

	public Die(int nSides) {
		mNumSides = nSides;
		random = new Random();
		// Initialize it with a roll just in case.
		roll();
	}

	public void roll() {
		mValueRolled = random.nextInt(mNumSides) + 1;
	}

	@Override
	public String toString() {
		// You don't need to make a resource from this.
		return String.format("Rolled %d on a %d-sided die", mValueRolled,
				mNumSides);
	}

	/**
	 * Sets the roll.
	 * 
	 * @param roll
	 * @return True iff the roll is valid for this die.
	 */
	public boolean setRoll(int roll) {
		mValueRolled = roll;
		return roll >= 1 && roll <= mNumSides;
	}

	public int getRoll() {
		return mValueRolled;
	}

	public int getNumSides() {
		return mNumSides;
	}
}
