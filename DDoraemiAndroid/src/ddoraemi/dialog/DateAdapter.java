package ddoraemi.dialog;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ddoraemi.creategroup.view.CalData;
import ddoraemi.start.R;

public class DateAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CalData> arrData;
	private LayoutInflater inflater;
	int toyear;
	int tomonth;
	int today;
	int afterweektodayvalue;

	public DateAdapter(Context c, ArrayList<CalData> arr, int year, int month,
			int day, int afterweektodayvalue) {
		this.context = c;
		this.arrData = arr;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.today = day;
		this.toyear = year;
		this.tomonth = month;
		this.afterweektodayvalue = afterweektodayvalue;

	}

	public int getCount() {
		return arrData.size();
	}

	public Object getItem(int position) {
		return arrData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_groupdateselectgrid,
					parent, false);
		}

		TextView ViewText = (TextView) convertView
				.findViewById(R.id.item_groudpdateselect_Tv_date);
		if (arrData.get(position) == null)
			ViewText.setText("");
		else {
			String year = String.valueOf(arrData.get(position).getYear());
			String month = String.valueOf(arrData.get(position).getMonth());
			String day = String.valueOf(arrData.get(position).getDay());
			int dayvalue = (Integer.parseInt(year) * 10000)
					+ (Integer.parseInt(month) * 100) + Integer.parseInt(day);
			ViewText.setText(arrData.get(position).getDay() + "");
			if (dayvalue > afterweektodayvalue) {
				arrData.get(position).setisPossibleCreateTrue(true);
				if ((arrData.get(position).getDayofweek() == 1 || arrData.get(
						position).getDayofweek() == 7)) {
					ViewText.setTextColor(Color.parseColor("#a40000"));
				} else {
					ViewText.setTextColor(Color.parseColor("#000000"));
				}

				ViewText.setPaintFlags(ViewText.getPaintFlags()
						| Paint.FAKE_BOLD_TEXT_FLAG);
			}

		}
		return convertView;
	}
}