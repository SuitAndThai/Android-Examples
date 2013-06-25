package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task implements Comparable<Task> {

	private String mName;
	private String mCourse;
	private GregorianCalendar mDateDue;
	private int mId;

	public Task() {

	}

	public Task(String name) {
		this.mName = name;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public GregorianCalendar getDateDue() {
		return mDateDue;
	}

	public void setDateDue(GregorianCalendar mDateDue) {
		this.mDateDue = mDateDue;
	}

	public void setDateDue(int year, int month, int dayOfMonth) {
		mDateDue = new GregorianCalendar();
		mDateDue.set(Calendar.HOUR, 0);
		mDateDue.set(Calendar.MINUTE, 0);
		mDateDue.set(Calendar.SECOND, 0);
		mDateDue.set(Calendar.MILLISECOND, 0);

		mDateDue.set(Calendar.YEAR, year);
		mDateDue.set(Calendar.MONTH, month);
		mDateDue.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	}

	public int getYearDue() {
		return mDateDue.get(Calendar.YEAR);
	}

	public int getonthDue() {
		return mDateDue.get(Calendar.MONTH);
	}

	public int getDayOfMonthDue() {
		return mDateDue.get(Calendar.DAY_OF_MONTH);
	}

	public String toString() {
		return mDateDue.get(Calendar.DAY_OF_MONTH) + " "
				+ getMonthString(mDateDue.get(Calendar.MONTH)) + " "
				+ getCourse() + " " + getName();
	}

	private String getMonthString(int i) {
		String returnString = "";
		switch (i) {
		case 0:
			returnString = "Jan";
			break;
		case 1:
			returnString = "Feb";
			break;
		case 2:
			returnString = "Mar";
			break;
		case 3:
			returnString = "Apr";
			break;
		case 4:
			returnString = "May";
			break;
		case 5:
			returnString = "June";
			break;
		case 6:
			returnString = "July";
			break;
		case 7:
			returnString = "Aug";
			break;
		case 8:
			returnString = "Sept";
			break;
		case 9:
			returnString = "Oct";
			break;
		case 10:
			returnString = "Nov";
			break;
		default:
			returnString = "Dec";
			break;
		}

		return returnString;
	}

	public int compareTo(Task another) {
		GregorianCalendar otherCalendar = another.getDateDue();

		if (otherCalendar.compareTo(getDateDue()) == 0)
			return getName().compareTo(another.getName());

		return -otherCalendar.compareTo(getDateDue());
	}

	public String getCourse() {
		return mCourse;
	}

	public void setCourse(String mCourse) {
		this.mCourse = mCourse;
	}
}
