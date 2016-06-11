package ddoraemi.search.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySearchListHelper extends SQLiteOpenHelper{
	private final static String DATABASE_NAME="Mylist.db";
	private final static int DATABASE_VERSION=1;
	public MySearchListHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE mylist (id VARCHAR(12) not null,searchtext TEXT not null, time TEXT,PRIMARY KEY(id,searchtext));");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS mylist");
		onCreate(db);
		
	}
	public void insert(SQLiteDatabase db,String id,String searchtext,String time)
	{
		db.execSQL("DELETE from mylist where searchtext='"+searchtext+"'and id='"+id+"'");
		db.execSQL("INSERT INTO mylist VALUES('"+id+"','"+searchtext+"','"+time+"');");
	}
	public ArrayList<String> selectMylist(SQLiteDatabase db,String id)
	{
		ArrayList<String> array=new ArrayList<>();
		String query="SELECT searchtext from mylist where id='"+id+"' Order By time DESC;";
		Cursor c = db.rawQuery(query, null);
		while(c.moveToNext())
		{
			String text=c.getString(c.getColumnIndex("searchtext"));
			array.add(text);
		}
		return array;
	}
	
}
