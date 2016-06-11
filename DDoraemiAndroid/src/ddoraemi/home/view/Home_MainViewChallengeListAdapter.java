package ddoraemi.home.view;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ddoraemi.appcontroller.AppController;
import ddoraemi.home.model.ProgramData;
import ddoraemi.start.R;
import ddoraemi.volley.VolleySingleton;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Home_MainViewChallengeListAdapter extends ArrayAdapter<ProgramData> {
    ArrayList<ProgramData> items;
    Context context;
    AppController app;
    RequestQueue mRequestQueue;
	ImageLoader mImageLoader;
	ImageView star[];
    public Home_MainViewChallengeListAdapter(Context context, int resource, ArrayList<ProgramData> objects) {
        super(context, resource, objects);
        this.context = context;
        items = objects;
        mRequestQueue = VolleySingleton.getInstance(context).getRequestQueue();
		mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
		app = (AppController)getContext().getApplicationContext();
    }

    public View getView(int position,View convertView, ViewGroup parent){
        View v = convertView;
        NetworkImageView avatar;
        if(v==null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            v = vi.inflate(R.layout.item_challengelist,null);
        }
        ProgramData p = items.get(position);
        if(p!=null){
        	star=new ImageView[5];
        	star[0]=(ImageView)v.findViewById(R.id.star_fill_img1);
        	star[1]=(ImageView)v.findViewById(R.id.star_fill_img2);
        	star[2]=(ImageView)v.findViewById(R.id.star_fill_img3);
        	star[3]=(ImageView)v.findViewById(R.id.star_fill_img4);
        	star[4]=(ImageView)v.findViewById(R.id.star_fill_img5);
        	int score=(int)(p.getP_grade()+0.5);
        	for(int i=0; i<5; i++)
        	{
        		star[i].setVisibility(View.GONE);
        	}
        	for(int i=0; i<score; i++)
        	{
        		star[i].setVisibility(View.VISIBLE);
        	}
            TextView challenge_name = (TextView) v.findViewById(R.id.challenge_name);
            TextView challenge_place = (TextView) v.findViewById(R.id.challenge_place);
            TextView challenge_price = (TextView) v.findViewById(R.id.challenge_price);
           
            if(challenge_name!=null){
            	challenge_name.setText(p.getP_name());
            }
            if(challenge_place!=null){
            	challenge_place.setText(p.getP_addr_1()+" "+p.getP_addr_2()+" "+p.getP_addr_3()
            			+" "+p.getP_addr_4()+" "+p.getP_addr_5()+" "+p.getP_addr_6());
            }
            if(challenge_price!=null){
            	if(!p.getP_cost_adult().isEmpty())
            		challenge_price.setText("성인 - "+p.getP_cost_adult()+"원 , 아이 - "+p.getP_cost_kid()+"원");
            	else
            		challenge_price.setText("가격 정보 없음");
            }
            avatar = (NetworkImageView)v.findViewById(R.id.iv_challenge_img);
            avatar.setDefaultImageResId(R.drawable.img_list_default);
    		avatar.setImageUrl(app.getServerIp()+"/program_photo/"+getItem(position).getP_photo_url(),mImageLoader);
        }
        return v;
    }

}