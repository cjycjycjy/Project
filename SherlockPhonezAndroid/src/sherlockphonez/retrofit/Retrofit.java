package sherlockphonez.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Retrofit {
	public static final String ROOT = "http://203.252.166.151:9090/sp";
	
	@POST("/signup.sp")
	public void join(@Query("u_name") String name,@Query("u_passwd") String passwd, @Query("u_email")String email,
			@Query("u_phonenum")String phoneNum,@Query("u_device_id") String deviceId, Callback<String> result);
	@POST("/updateuser.sp")
	public void changeinfo(@Query("u_name") String name,@Query("u_passwd") String passwd, @Query("u_email")String email,
			@Query("u_phonenum")String phoneNum,@Query("u_device_id") String deviceId, Callback<String> result);
	@POST("/sendregistrationid.sp")
	public void sendRegiId(@Query("u_register_id") String regiId, @Query("u_phonenum") String phoneNum, Callback<String> result);
	
	@POST("/signin.sp")
	public void login(@Query("u_phonenum") String phoneNum, @Query("u_passwd") String passwd, Callback<JsonObject> result);
	
	@POST("/sendgcm.sp")
	public void sendgcm(@Query("message") String msg, @Query("u_phonenum") String myphoneNum, Callback<String> result);
	
	@POST("/startservice.sp")
	public void startService(@Query("u_phonenum") String phoneNum, Callback<String> result);
	
	@POST("/stopservice.sp")
	public void stopService(@Query("u_phonenum") String phoneNum, Callback<String> result);
	
	@POST("/registerlocation.sp")
	public void registerlocation(@Query("u_phonenum") String phoneNum, @Query("l_lat") double latitude,
			@Query("l_long") double longitude, Callback<String> result);
	
	@POST("/getlocation.sp")
	public void getlocation(@Query("u_phonenum") String phoneNum, Callback<JsonObject> result);
	
	@POST("/sendimage.sp")
	public void sendImage(@Body JsonObject obj, Callback<String> result);
	@POST("/getimage.sp")
	public void getImage(@Query("u_phonenum") String phonenum, Callback<JsonArray> result);
	
	@POST("/findpassword.sp")
	public void findpasswd(@Query("u_phonenum") String phonenum, @Query("u_email") String email, Callback<String> result);
}
