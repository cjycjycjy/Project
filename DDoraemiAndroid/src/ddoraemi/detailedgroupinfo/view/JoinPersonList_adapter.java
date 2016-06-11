package ddoraemi.detailedgroupinfo.view;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.detailedgroupinfo.model.QnA;
import ddoraemi.start.R;
import ddoraemi.start.User;
import ddoraemi.volley.VolleySingleton;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class JoinPersonList_adapter extends ArrayAdapter<User>{

	ArrayList<User> item;
	Context context;
	ImageLoader mImageLoader;
	RequestQueue mRequestQueue;
	AppController app;
	
	ArrayList<User> checkeduser;



	public JoinPersonList_adapter(Context context, int resource, ArrayList<User> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.item = objects;
		this.context = context;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)context.getApplicationContext();
		checkeduser = new ArrayList<>();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
			v = vi.inflate(R.layout.item_joinpersonslist,null);

		}
		final User user = item.get(position);
		if(user!=null){
			final ToggleButton btn_toggle_check = (ToggleButton)v.findViewById(R.id.tb_nonchecked);
			TextView tv_u_id = (TextView)v.findViewById(R.id.tv_joinperson_u_id);
			TextView tv_u_addr = (TextView)v.findViewById(R.id.tv_u_addr);
			ImageView iv_phone = (ImageView)v.findViewById(R.id.iv_phone);
			ImageView iv_text = (ImageView)v.findViewById(R.id.iv_text);
			tv_u_id.setText(user.getU_id());
			tv_u_addr.setText(user.getU_addr1()+" "+user.getU_addr2()+" "+user.getU_addr3()+" ");
			
			iv_phone.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_DIAL);
					String smsto=user.getU_phone().toString();
					Uri sms= Uri.parse("tel:"+smsto);
					
					
					intent.setData(sms);
					context.startActivity(intent);
				}
			});
			
			iv_text.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_SENDTO);
					String smsto=user.getU_phone().toString();
					Uri sms= Uri.parse("sms:"+smsto);
					
					
					intent.setData(sms);
					context.startActivity(intent);
				}
			});
			btn_toggle_check.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(btn_toggle_check.isChecked()){
						btn_toggle_check.setBackgroundResource(R.drawable.icon_checked);
						checkeduser.add(user);
					}else{
						btn_toggle_check.setBackgroundResource(R.drawable.icon_notchecked);
						checkeduser.remove(user);
					}
				}
			});
			if(!getItem(position).getU_photo_url().equalsIgnoreCase("empty")){
				NetworkImageView avatar;
				avatar = (NetworkImageView)v.findViewById(R.id.iv_joinperson_u_img);
				avatar.setImageUrl(app.getServerIp()+"/user_photo/"+getItem(position).getU_photo_url(),mImageLoader);
			}

		}
		return v;
	}

	public ArrayList<User> getcheckeduser(){
		return checkeduser;
	}


}
