package edu.rosehulman.listviewbaseadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

public class RowNumberAdapter extends BaseAdapter implements Adapter,
		ListAdapter, SpinnerAdapter {

	private int mNumRows;
	private Context mContext;
	private String[] mNamesArray;

	public RowNumberAdapter(Context context) {
		mContext = context;
		mNumRows = 5;
		mNamesArray = context.getResources().getStringArray(R.array.months);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mNumRows;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mNamesArray[position % 12];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		// TextView view = null;
		// Button view;
		// ImageView view;
		RowView view;
		if (null == convertView) {
			// if there isn't one to recycle, init a new view
			// view = new TextView(this.mContext);
			// view = new Button(mContext);
			// view.setTextSize(18); // Properties where all views are the same
			// view = new ImageView(this.mContext);
			// view.setImageResource(R.drawable.ic_launcher);
			view = new RowView(this.mContext);
		} else {
			// use the recycled view (convert this view)
			// view = (TextView) convertView;
			// view = (Button) convertView;
			// view = (ImageView) convertView;
			view = (RowView) convertView;
		}
		// Properties where each view is different
		// view.setText(" Row " + position);
		// view.setTextColor(Color.rgb(position * 10, 255 - position * 10,
		// 200));
		view.setLeftText("" + (position + 1) + ".");
		view.setRightText(mNamesArray[position % 12]);

		return view;
	}

	public void addView() {
		mNumRows++;
	}
}
