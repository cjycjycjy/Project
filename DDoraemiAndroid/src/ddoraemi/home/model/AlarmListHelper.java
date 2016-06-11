package ddoraemi.home.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmListHelper extends SQLiteOpenHelper{
	private final static String DATABASE_NAME="Myalarm.db";
	private final static int DATABASE_VERSION=1;
	public AlarmListHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE alarmlist (pk Integer primary key, type VARCHAR(100), u_id VARCHAR(50),p_g_name VARCHAR(100),"
				+ "year int, month int, day int, hour int, minute int, p_g_id int, img_url varchar(255), message text, ischecked int, my_id VARCHAR(100));");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS alarmlist");
		onCreate(db);
		
	}
	public void insert(SQLiteDatabase db,PushMessage msg,String myid)
	{
		db.execSQL("INSERT INTO alarmlist VALUES(NULL"+",'"+msg.getType()+"','"+msg.getU_id()+"','"+msg.getP_g_name()+"'"
				+ ","+msg.getYear()+","+msg.getMonth()+","+msg.getDay()+","+msg.getHour()+","+msg.getMinute()+
						","+msg.getP_g_id()+",'"+msg.getImg_url()+"','"+msg.getMessage()+"',0,"+"'"+myid+"');");
	}
	public void check(SQLiteDatabase db,int pk,String id){
		db.execSQL("update alarmlist set ischecked = 1 where my_id='"+id+"' and pk = "+pk);
	}
	public ArrayList<PushMessage> selectMylist(SQLiteDatabase db,String myid)
	{
		ArrayList<PushMessage> array=new ArrayList<>();
		String query="SELECT * from alarmlist where my_id='"+myid+"' order by pk desc";
		Cursor c = db.rawQuery(query, null);
		while(c.moveToNext())
		{
			
			int pk=c.getInt(c.getColumnIndex("pk"));
			String type=c.getString(c.getColumnIndex("type"));
			String u_id=c.getString(c.getColumnIndex("u_id"));
			String p_g_name=c.getString(c.getColumnIndex("p_g_name"));
			int year=c.getInt(c.getColumnIndex("year"));
			int month=c.getInt(c.getColumnIndex("month"));
			int day=c.getInt(c.getColumnIndex("day"));
			int hour=c.getInt(c.getColumnIndex("hour"));
			int minute=c.getInt(c.getColumnIndex("minute"));
			int p_g_id=c.getInt(c.getColumnIndex("p_g_id"));
			String img_url=c.getString(c.getColumnIndex("img_url"));
			String message=c.getString(c.getColumnIndex("message"));
			int ischecked = c.getInt(c.getColumnIndex("ischecked"));
			PushMessage msg = new PushMessage(pk,type, u_id, p_g_name, year, month, day, hour, minute, p_g_id, img_url, message,ischecked);
			array.add(msg);
		}
		return array;
	}
	public int count(SQLiteDatabase db,String myid){
		ArrayList<PushMessage> array = new ArrayList<>();
		String query="SELECT * from alarmlist where ischecked = 0 and my_id='"+myid+"'";
		Cursor c = db.rawQuery(query, null);
		while(c.moveToNext())
		{
			int pk=c.getInt(c.getColumnIndex("pk"));
			String type=c.getString(c.getColumnIndex("type"));
			String u_id=c.getString(c.getColumnIndex("u_id"));
			String p_g_name=c.getString(c.getColumnIndex("p_g_name"));
			int year=c.getInt(c.getColumnIndex("year"));
			int month=c.getInt(c.getColumnIndex("month"));
			int day=c.getInt(c.getColumnIndex("day"));
			int hour=c.getInt(c.getColumnIndex("hour"));
			int minute=c.getInt(c.getColumnIndex("minute"));
			int p_g_id=c.getInt(c.getColumnIndex("p_g_id"));
			String img_url=c.getString(c.getColumnIndex("img_url"));
			String message=c.getString(c.getColumnIndex("message"));
			int ischecked = c.getInt(c.getColumnIndex("ischecked"));
			PushMessage msg = new PushMessage(pk,type, u_id, p_g_name, year, month, day, hour, minute, p_g_id, img_url, message,ischecked);
			array.add(msg);
		}
		return array.size();
	}
}
