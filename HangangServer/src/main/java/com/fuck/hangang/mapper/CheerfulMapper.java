package com.fuck.hangang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fuck.hangang.vo.CheerfulReplyOfReplyVo;
import com.fuck.hangang.vo.CheerfulReplyVo;
import com.fuck.hangang.vo.CheerfulVo;
import com.fuck.hangang.vo.PushVo;
import com.fuck.hangang.vo.RegistrationIdVo;
import com.fuck.hangang.vo.UserVo;

@Repository
public interface CheerfulMapper {
	public List<CheerfulVo> getteamcheerfulfirst(@Param("t_num") int t_num, @Param("lastnum") int lastnum,
			@Param("count") int count);

	public List<CheerfulVo> getteamcheerfullast(@Param("t_num") int t_num, @Param("lastnum") int lastnum);

	public List<CheerfulVo> gettotalcheerfulfirst(@Param("lastnum") int lastnum, @Param("count") int count);

	public List<CheerfulVo> gettotalcheerfullast(@Param("lastnum") int lastnum);

	public int getcheerfulcommentnum(@Param("c_num") int c_num);

	public void registcheerful(@Param("u_id") String u_id, @Param("t_num") int t_num,
			@Param("ch_contents") String ch_content, @Param("ch_photo") String ch_photo);

	public UserVo getcheerfuluserinfo(@Param("u_id") String u_id);

	public List<CheerfulReplyVo> getcheerfulreplyfirst(@Param("c_num") int c_num, @Param("lastnum") int lastnum,
			@Param("count") int count);

	public List<CheerfulReplyVo> getcheerfulreplylast(@Param("c_num") int c_num, @Param("lastnum") int lastnum);

	public int getcheerfulreplycount(@Param("c_num") int c_num);
	public int getcheerfulreplyofreplycount(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num);


	
	public void registcheerfulreply(@Param("c_num") int c_num, @Param("u_id") String u_id,
			@Param("c_r_content") String c_r_content);

	public List<CheerfulReplyOfReplyVo> getcheerfulreplyofreplyfirst(@Param("c_num") int c_num,
			@Param("c_r_num") int c_r_num);

	public List<CheerfulReplyOfReplyVo> getcheerfulreplyofreplylast(@Param("c_num") int c_num,
			@Param("c_r_num") int c_r_num, @Param("lastnum") int lastnum);


	
	public void registcheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("u_id") String u_id, @Param("c_rr_content") String c_rr_content);

	public void deletecheerfulreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num);

	public void deletecheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_rr_num") int c_rr_num);

	public String getcheerfulphotourl(@Param("c_num") int c_num);

	public void deletecheerful(@Param("c_num") int c_num);

	public void updatecheerfulreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_r_content") String c_r_content);

	public void updatecheerfulreplyofreply(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num,
			@Param("c_rr_num") int c_rr_num, @Param("c_rr_content") String c_rr_content);
	
	public RegistrationIdVo getRegiIdOfUser(@Param("c_num") int c_num);
	public RegistrationIdVo getRegiIdOfUserComment(@Param("c_r_num") int c_r_num);
	public CheerfulVo getcheerfulOne(@Param("c_num") int c_num);
	public String checkReplyUser(@Param("c_num") int c_num);
	public String checkReplyOfReplyUser(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num);
	public int checkPushUser(@Param("id") String id);
	
	
	public CheerfulReplyVo getcheerfulreplyone(@Param("c_num") int c_num, @Param("c_r_num") int c_r_num);
	public void registPushListAboutReply(PushVo pushVo);
	public void registPushListAboutReplyOfReply(PushVo pushVo);
	
	public void updatePushisChecked(@Param("pm_num") int pm_num);
	public void updatecheerful(@Param("c_num") int c_num, @Param("c_content") String c_content, @Param("c_photourl") String c_photourl);
}
