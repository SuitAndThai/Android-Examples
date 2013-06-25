package edu.rosehulman.listviewarrayadapter;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class StarTextAdapter extends ArrayAdapter<StarText> {
	public StarTextAdapter(Context context, int resource,
			int textViewResourceId, List<StarText> objects) {
		super(context, resource, textViewResourceId, objects);
	}
}
