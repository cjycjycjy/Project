package ddoraemi.retrofit;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface Retrofit {
	public static final String ROOT = "http://203.252.166.151:9090/skp";

	@GET("/getprogram.skp")
	public void getProgramList_availableGroup(@Query("u_id") String u_id ,Callback<JsonObject> callback);

	@GET("/getafterword.skp")
	public void getAfterword(@Query("p_id") int p_id,
			Callback<JsonObject> callback);

	@GET("/getquestion.skp")
	public void getQuestion(@Query("g_id") int p_id,
			Callback<JsonArray> callback);

	@GET("/getprogram.skp")
	public void getProgramList_type(@Query("category") String category,
			@Query("e_name") String e_name, Callback<JsonArray> callback);

	@GET("/getprogram.skp")
	public void getProgramList_location(@Query("category") String category,
			@Query("p_addr_1") String p_addr_1, Callback<JsonArray> callback);
	
	@GET("/getprogrampid.skp")
	public void getprogram_pid(@Query("p_id") int p_id, Callback<JsonObject> callback);
	
	@GET("/getquestionone.skp")
	public void getQna(@Query("q_id") int q_id, Callback<JsonObject> callback);
	
	@GET("/getgatheringone.skp")
	public void getGroup(@Query("g_id") int g_id, Callback<JsonObject> callback);
	
	@GET("/getjoinuser.skp")
	public void getjoinuser(@Query("g_id") int g_id, Callback<JsonObject> callback);
	@GET("/registerafterwordreply.skp")
	public void setReply(@Query("a_id") int a_id, @Query("u_id") String u_id,
			@Query("a_r_content") String a_r_content,
			Callback<JsonArray> callback);
	
	@GET("/modifyafterwordreply.skp")
	public void modifyReply(@Query("a_id") int a_id,@Query("a_r_id") int a_r_id,@Query("a_r_content") String a_r_content,
			Callback<JsonObject> callback);
	
	@GET("/deleteafterwordreply.skp")
	public void deleteReply(@Query("a_id") int a_id,@Query("a_r_id") int a_r_id,Callback<JsonObject> callback);

	@GET("/registerquestionreply.skp")
	public void setQnAReply(@Query("q_id") int q_id,
			@Query("u_id") String u_id,
			@Query("q_r_content") String q_r_content,
			Callback<JsonArray> callback);
	
	@GET("/modifyquestionreply.skp")
	public void modifyQnAReply(@Query("q_id") int q_id,
			@Query("q_r_id") int q_r_id,
			@Query("q_r_content") String q_r_content,
			Callback<JsonObject> callback);
	
	@GET("/deletequestionreply.skp")
	public void deleteQnAReply(@Query("q_id") int q_id,
			@Query("q_r_id") int q_r_id,
			Callback<JsonObject> callback);
	@GET("/deletequestion.skp")
	public void deleteQnA(@Query("q_id") int q_id, Callback<JsonObject> result);
	
	@GET("/registerbookmark.skp")
	public void registerbookmark(@Query("p_id") int p_id, @Query("u_id") String u_id, Callback<String> result);
	
	@POST("/modifyuserphoto.skp")
	public void modifyUserPhoto(@Body JsonObject info, Callback<JsonObject> result);
	
	@GET("/unregisterbookmark.skp")
	public void unregisterbookmark(@Query("p_id") int p_id, @Query("u_id") String u_id, Callback<String> result);

	@GET("/joingathering.skp")
	public void joingroup(@Query("g_id")int g_id, @Query("u_id") String u_id,@Query("persons") int hopeperson, Callback<String> result);
	
	@GET("/outgathering.skp")
	public void cancelgroup(@Query("g_id")int g_id, @Query("u_id") String u_id, Callback<JsonObject> result);	
	@GET("/deletegathering.skp")
	public void deletegroup(@Query("g_id") int g_id, Callback<String> result);
	
	@GET("/requestprogram.skp")
	public void requestadmin(@Query("u_id") String u_id, @Query("a_name") String a_name,@Query("a_phonenum") String a_phonenum,
			@Query("a_p_phonenum") String a_p_phonenum,@Query("a_email") String a_email, @Query("a_programinfo") String a_programinfo
			,Callback<String> result);
	// ////////ȸ������ ���� ���/////////////
	@POST("/signup.skp")
	// /ȸ������ ���
	public void requestJoin(@Body JsonObject info, Callback<String> callback);

	@POST("/modifyfavoriteprogram.skp")
	// /선호체험 바꾸기
	public void requestChangeFavorite(@Body JsonObject info, Callback<String> callback);

	@POST("/changepassword.skp")
	// /비밀번호 바꾸기
	public void requestChangePasswd(@Query("u_id") String u_id,@Query("password") String passwd, Callback<String> callback);

	@POST("/findpassword.skp")
	public void findpasswd(@Query("u_id")String u_id, @Query("u_phone") String u_phone, Callback<String> result);
	
	@GET("/idcheck.skp")
	// /���̵� �ߺ�Ȯ��
	public void getConfirmOverlapID(@Query("u_id") String info,
			Callback<String> callback);

	@GET("/getversion.skp")
	public void getVersion(@Query("version") String version, Callback<String> callback);
	// ///////////////////////////////

	// /////////�α��� ���� ���/////////////
	@FormUrlEncoded
	@POST("/login.skp")
	// /�α��� ���
	public void requestLogin(@Field("u_id") String id,
			@Field("password") String password, Callback<JsonObject> callback);

	// ///////////////////////////////

	// ////////모임 개설///////////////
	@GET("/registergathering.skp")
	public void sendCreateGroupInfo(@Query("p_id") int p_id,
			@Query("u_id") String u_id, @Query("g_name") String g_name,
			@Query("g_want_persons") int g_want_persons,
			@Query("g_info") String g_info,
			@Query("g_open_year") int g_open_year,
			@Query("g_open_month") int g_open_month,
			@Query("g_open_day") int g_open_day,
			@Query("g_close_year") int g_close_year,
			@Query("g_close_month") int g_close_month,
			@Query("g_close_day") int g_close_day,
			@Query("g_start_year") int g_start_year,
			@Query("g_start_month") int g_start_month,
			@Query("g_start_day") int g_start_day,
			@Query("g_start_hour") int g_start_hour,
			@Query("g_start_min") int g_start_min,
			@Query("g_end_hour") int g_end_hour,
			@Query("g_end_min") int g_end_min, Callback<JsonObject> callback);

	@GET("/getprogramafterwordphoto.skp")
	public void getPicture(@Query("p_id") int p_id, Callback<JsonArray> callback);

	@GET("/registerquestion.skp")
	public void setQnA(@Query("u_id") String u_id, @Query("g_id") int g_id,
			@Query("q_content") String q_content, Callback<JsonArray> callback);
	@GET("/getmypage.skp")
	public void getMyPageInfo(@Query("u_id") String u_id,Callback<JsonObject> callback);
	@POST("/registerafterword.skp")
	public void saveAfterWord(@Body JsonObject info,Callback<String> callback);
	@POST("/modifyafterword.skp")
	public void modifyAfterWord(@Body JsonObject info,Callback<JsonObject> callback);
	@GET("/deleteafterword.skp")
	public void deleteAfterWord(@Query("a_id") int a_id,Callback<String> callback);
	@GET("/getadminprogram.skp")
	public void getAdmin_Program(@Query("u_id")String u_id,Callback<JsonObject> callback);
	@GET("/getadminprograminfo.skp")
	public void getAdmin_ProgramInfo(@Query("p_id")int u_id,Callback<JsonObject> callback);
	@GET("/getverificationcode.skp")
	public void getVerificationcode(@Query("u_phone") String phone,Callback<JsonObject> callback);
	@GET("/setalarm.skp")
	public void setAlarm(@Query("u_id") String u_id,@Query("alarm") int alarm, Callback<String> callback);
	@GET("/getsearch.skp")
	public void getSearch(@Query("content") String content,Callback<JsonObject> callback);
	@GET("/updateregisterid.skp")
	public void modify_register(@Query("u_id") String u_id,@Query("u_register_id") String register,Callback<String> callback);


}
