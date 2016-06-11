package ddoraemi.dialog;

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

public class Adapter_Dialog_DateList extends ArrayAdapter<Group> {
	ArrayList<Group> items;
	Context context;
	int resource;
	int leadtime;
	Dialog_GroupDateSelect dialog;

	public Adapter_Dialog_DateList(Context context,Dialog_GroupDateSelect dialog, int resource,
			ArrayList<Group> objects, int lead_time) {
		super(context, resource, objects);
		this.dialog=dialog;
		this.context = context;
		items = objects;
		this.leadtime = lead_time;
		this.resource = resource;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(resource, null);
		}
		final int itemposition=position;
		Group p = items.get(position);
		TextView grouptime = (TextView) v
				.findViewById(R.id.item_groudpdateselectlist_tv_time);
		TextView available = (TextView) v
				.findViewById(R.id.item_groudpdateselectlist_tv_availablegroudp);
		TextView end = (TextView) v
				.findViewById(R.id.item_groudpdateselectlist_tv_endedgroudp);
		ImageView delete = (ImageView) v
				.findViewById(R.id.item_groudpdateselectlist_tv_deletegroudp);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.pushdeletelist(itemposition);
			}
		});
		if (p != null) {

			if (grouptime != null) {
				int starthour = p.getG_start_hour();
				int startmin = p.getG_start_minute();
				int leadtime = (starthour * 60) + startmin + this.leadtime;
				int endhour = leadtime / 60;
				int endmin = leadtime % 60;
				grouptime.setText(starthour + "시 " + startmin + "분 ~ "
						+ endhour + "시 " + endmin + "분");

			}

			if (p.getG_status() == 1) {
				end.setVisibility(View.GONE);
				delete.setVisibility(View.GONE);
				available.setVisibility(View.VISIBLE);
			} else if (p.getG_status() == 0) {
				end.setVisibility(View.VISIBLE);
				delete.setVisibility(View.GONE);
				available.setVisibility(View.GONE);
			} else {
				end.setVisibility(View.GONE);
				delete.setVisibility(View.VISIBLE);
				available.setVisibility(View.GONE);

			}

		} else {
			grouptime.setText("");
			end.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
			available.setVisibility(View.GONE);

		}
		return v;
	}

}
