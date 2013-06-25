package edu.rosehulman.listviewarrayadapter;

public class StarText {
	private String mText;
	private int mNumStars;

	public StarText(String text) {
		this.mText = text;
		this.mNumStars = 0;
	}

	// Override toString()
	@Override
	public String toString() {
		return mText;
	}

	public void addStar() {
		this.mNumStars++;
	}

	public String getStarString() {
		String starString = "";
		for (int i = 0; i < this.mNumStars; i++) {
			starString += "*";
		}
		return starString;
	}

	public int getNumStars() {
		return mNumStars;
	}
}