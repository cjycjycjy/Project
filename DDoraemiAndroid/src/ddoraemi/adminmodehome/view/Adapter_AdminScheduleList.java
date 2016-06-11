package ddoraemi.adminmodehome.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ddoraemi.home.model.Group;
import ddoraemi.start.R;

public class Adapter_AdminScheduleList extends ArrayAdapter<Group> {
	ArrayList<Group> items;
	Context context;
	int resource;
	public Adapter_AdminScheduleList(Context context, int resource,
			ArrayList<Group> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		this.resource = resource;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(resource, null);
		}
		final int itemposition = position;
		Group p = items.get(position);
		TextView grouptime = (TextView) v
				.findViewById(R.id.item_adminschedulelist_Tv_date);
		TextView available = (TextView) v
				.findViewById(R.id.item_adminschedulelist_tv_availablegroudp);
		TextView end = (TextView) v
				.findViewById(R.id.item_adminschedulelist_tv_endedgroudp);
		if (p != null) {

			if (grouptime != null) {
				int starthour = p.getG_start_hour();
				int startmin = p.getG_start_minute();
				int endhour = p.getG_end_hour();
				int endmin = p.getG_end_minute();
				grouptime.setText(starthour + "시 " + startmin + "분 ~ "
						+ endhour + "시 " + endmin + "분");

			}

			if (p.getG_status() == 1) {
				end.setVisibility(View.GONE);
				available.setVisibility(View.VISIBLE);
			} else {
				end.setVisibility(View.VISIBLE);
				available.setVisibility(View.GONE);
			}
		} else {
			grouptime.setText("");
			end.setVisibility(View.GONE);
			available.setVisibility(View.GONE);

		}
		return v;
	}

}
