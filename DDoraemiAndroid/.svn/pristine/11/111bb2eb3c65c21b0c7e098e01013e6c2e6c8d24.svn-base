package ddoraemi.adminmodehome.view;

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
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;

public class AdminHomeSideMenu_Challenge_adapter  extends ArrayAdapter<ProgramData> {
	PopupWindow mDropdown;
	ArrayList<ProgramData> items;
	Context context;

	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	

	public  AdminHomeSideMenu_Challenge_adapter(Context context, int resource, ArrayList<ProgramData> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
	}


	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_adminsidemenu_cha_list, null);

			app = (AppController) v.getContext().getApplicationContext();
		}
		final ProgramData p = items.get(position);
		if (p != null) {
			TextView p_name=(TextView)v.findViewById(R.id.item_adminsidemenu_cha_list_name);
			p_name.setText(p.getP_name());
		}
		return v;
	}
	
	

}
