package ddoraemi.home.view;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.home.model.PushMessage;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;

public class Adapter_AlarmList extends ArrayAdapter<PushMessage> {
	ArrayList<PushMessage> items;
	Context context;
	AppController app;
	RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	ImageView star[];
	Calendar cal;
	long now_day;
	int nowhour;
	int nowmin;
	int nowtimes;
	public Adapter_AlarmList(Context context, int resource,
			ArrayList<PushMessage> objects) {
		super(context, resource, objects);
		this.context = context;
		items = objects;
		mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController) getContext().getApplicationContext();
		cal=Calendar.getInstance();
		nowhour=cal.get(Calendar.HOUR_OF_DAY);
		nowmin=cal.get(Calendar.MINUTE);
		nowtimes=nowhour*60+nowmin;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		NetworkImageView avatar;
		TextView date;
		TextView message;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_alarm, null);
		}
		PushMessage p = items.get(position);
		if (p != null) {
			LinearLayout layout = (LinearLayout) v
					.findViewById(R.id.item_alarm_layout);
			ImageView frame = (ImageView) v
					.findViewById(R.id.item_alarm_frameimg);
			avatar = (NetworkImageView) v
					.findViewById(R.id.item_alarm_networdimg);
			date = (TextView) v.findViewById(R.id.item_alarm_datetv);
			message = (TextView) v.findViewById(R.id.item_alarm_messagetv);
			if (p.getIschecked() == 0) {
				layout.setBackgroundColor(Color.parseColor("#eeeeee"));
				frame.setImageResource(R.drawable.circle_gray);
			} else {
				layout.setBackgroundColor(Color.parseColor("#ffffff"));
				frame.setImageResource(R.drawable.circle_yellow);
			}
			if (p.getType().equals("b")) {
				SpannableString messagess = new SpannableString(app.getId()
						+ "님이 북마크한 " + p.getP_g_name() + " 관련 모임이 생성되었습니다.");
				messagess.setSpan(
						new StyleSpan(android.graphics.Typeface.BOLD), 0, app
								.getId().length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				messagess.setSpan(
						new StyleSpan(android.graphics.Typeface.BOLD), app
								.getId().length() + 7, app.getId().length() + 7
								+ p.getP_g_name().length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				message.setText(messagess, BufferType.SPANNABLE);
				avatar.setDefaultImageResId(R.drawable.img_list_default);
				avatar.setImageUrl(app.getServerIp() + "/program_photo/"
						+ getItem(position).getImg_url(), mImageLoader);
			} else if (p.getType().equals("q")) {
				SpannableString messagess = new SpannableString(p.getU_id()
						+ "님이 " + p.getP_g_name() + " 모임에 댓글을 남겼습니다.");
				messagess.setSpan(
						new StyleSpan(android.graphics.Typeface.BOLD), 0, p.getU_id().length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				messagess.setSpan(
						new StyleSpan(android.graphics.Typeface.BOLD), p.getU_id().length() + 3, p.getU_id().length() + 4								+ p.getP_g_name().length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				message.setText(messagess, BufferType.SPANNABLE);
				avatar.setDefaultImageResId(R.drawable.usericon);
				avatar.setImageUrl(app.getServerIp() + "/user_photo/"
						+ getItem(position).getImg_url(), mImageLoader);
			} else if (p.getType().equals("q_r")) {
				SpannableString messagess=new SpannableString(p.getU_id()+"님이  "+p.getP_g_name()+" 모임에 남긴 내 댓글에 답글을 남겼습니다.");
				messagess.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, p.getU_id().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				messagess.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), p.getU_id().length()+3,p.getU_id().length()+4+p.getP_g_name().length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				message.setText(messagess,BufferType.SPANNABLE);
				avatar.setDefaultImageResId(R.drawable.usericon);
				avatar.setImageUrl(app.getServerIp() + "/user_photo/"
						+ getItem(position).getImg_url(), mImageLoader);

			} else {
				String s=p.getP_g_name()+"모임 참여가 확정될 예정입니다. 못가시는 분은 취소를 해주세요.";
				SpannableString messagess=new SpannableString(s);
				messagess.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, p.getP_g_name().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				messagess.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), p.getP_g_name().length()+7,p.getP_g_name().length()+9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				messagess.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), p.getP_g_name().length()+18,s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			    
			    message.setText(messagess,BufferType.SPANNABLE);
				avatar.setDefaultImageResId(R.drawable.img_list_default);
				avatar.setImageUrl(app.getServerIp() + "/program_photo/"
						+ getItem(position).getImg_url(), mImageLoader);

			}
			String week=p.getWeek();
			int hour=p.getHour();
			int minute=p.getMinute();
			int time=hour*60+minute;
			if(p.getd_day()==0)//오늘일때
			{
				int times=p.getHour()*60+p.getMinute();
				if(nowtimes-times<60)
				{
					date.setText((nowtimes-times)%60+"분 전");
				}
				else
				{
					date.setText((nowtimes-times)/60+"시간 전");	
				}
			}
			else if(p.getd_day()==1)
			{
				if(time>720)
				{
					date.setText("어제 오전"+(hour-12)+":"+p.getMinute());	
				}
				else
				{
					date.setText("어제 오후"+(hour-12)+":"+p.getMinute());
				}
			}
			else if(p.getd_day()<7)
			{
				if(time>720)
				{
					date.setText(week+"요일 오전"+(hour-12)+":"+p.getMinute());	
				}
				else
				{
					date.setText(week+"요일 오후"+(hour-12)+":"+p.getMinute());
				}
			}
			else
			{
				if(time>720)
				{
					date.setText(p.getYear()+"년" +p.getMonth()+"월 "+p.getDay()+"일 오후"+(hour-12)+":"+p.getMinute());	
				}
				else
				{
					date.setText(p.getYear()+"년" +p.getMonth()+"월 "+p.getDay()+"일 오전"+(hour)+":"+p.getMinute());	
				}
			}
		}
		return v;
	}
}
