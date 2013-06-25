package edu.rosehulman.hellogridview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ButtonAdapter extends BaseAdapter {
	private Context mContext;
	private static final int NBUTTONS = 16;

	public ButtonAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return NBUTTONS;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {
		Button button;

		if (convertView == null) {

			button = new Button(mContext);
			button.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.MATCH_PARENT,
					GridView.LayoutParams.WRAP_CONTENT));
			button.setPadding(8, 8, 8, 8);

			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT)
							.show();
				}

			});

		} else {
			button = (Button) convertView;
		}
		button.setText(position + "");
		button.setTextColor(Color.BLACK);

		button.setId(position);

		return button;
	}
}
