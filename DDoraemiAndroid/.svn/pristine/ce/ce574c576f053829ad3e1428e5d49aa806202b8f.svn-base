package ddoraemi.dialog;

import java.util.ArrayList;

import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Dialog_Admin_SelectProgramlist_adapter extends ArrayAdapter<ProgramData>{

	ArrayList<ProgramData> items;
	Context context;
	public Dialog_Admin_SelectProgramlist_adapter(Context context, int resource, ArrayList<ProgramData> object) {
		super(context, resource, object);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.items = object;
	}

	public View getView(int position,View convertView, ViewGroup parent){
		View v = convertView;

		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
			v = vi.inflate(R.layout.item_admin_selectprogramlist,null);
		}
		ProgramData p = items.get(position);
		if(p!=null){
			TextView tv_p_name = (TextView)v.findViewById(R.id.tv_admin_selected_programname);
			tv_p_name.setText(p.getP_name());
		}
		return v;
	}

}
