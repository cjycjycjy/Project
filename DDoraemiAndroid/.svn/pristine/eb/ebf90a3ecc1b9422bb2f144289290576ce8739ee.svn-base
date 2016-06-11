package ddoraemi.search.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import ddoraemi.appcontroller.AppController;
import ddoraemi.start.R;

public class Adapter_SearchViewMylist extends ArrayAdapter<String> {
	PopupWindow mDropdown;
	ArrayList<String> items;
	Context context;

	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;

	public Adapter_SearchViewMylist(Context context, int resource,
			ArrayList<String> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_mylist, null);

			app = (AppController) v.getContext().getApplicationContext();
		}
		final String p = items.get(position);
		if (p != null) {
			TextView mylisttext = (TextView) v
					.findViewById(R.id.item_mylist_mytext);
			mylisttext.setText(p);
		}
		return v;
	}
}