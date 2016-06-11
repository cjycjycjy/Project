package com.myserver.skp.controller;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myserver.skp.dao.GatheringDao;
import com.myserver.skp.dao.QuestionDao;
import com.myserver.skp.dao.QuestionReplyDao;
import com.myserver.skp.dao.UserDao;
import com.myserver.skp.util.GCMSender;
import com.myserver.skp.vo.QuestionReplyVo;
import com.myserver.skp.vo.QuestionVo;
import com.myserver.skp.vo.UserVo;

@Controller
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(AfterwordController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Resource
	private QuestionDao questionDao;

	@Resource
	private UserDao userDao;

	@Resource
	private GatheringDao gatheringDao;

	@Resource
	private QuestionReplyDao questionReplyDao;

	@RequestMapping(value = "/getquestion", method = RequestMethod.GET)
	public @ResponseBody List<QuestionVo> getQuestion(@RequestParam("g_id") int g_id) {
		List<QuestionVo> questionList = questionDao.getSelectAllofQuestion(g_id);
		return questionList;

	}
	
	@RequestMapping(value = "/getquestionone", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> getQuestionOne(@RequestParam("q_id") int q_id) {
		HashMap<String,T> result = new HashMap<String, T>();
		try{
			QuestionVo question = questionDao.getSelectQuestion(q_id);
			result.put("result", (T)"200");
			result.put("question", (T)question);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;

	}

	@RequestMapping(value = "/registerquestionreply", method = RequestMethod.GET)
	public @ResponseBody <T> List<QuestionReplyVo> registerQuestionReply(@RequestParam("u_id") String u_id,
			@RequestParam("q_id") int q_id,
			@RequestParam("q_r_content") String q_r_content) {

		GregorianCalendar today = new GregorianCalendar();
		int q_r_year = today.get ( today.YEAR );
		int q_r_month = today.get ( today.MONTH ) + 1;
		int q_r_day = today.get ( today.DAY_OF_MONTH ); 
		int q_r_hour = today.get(today.HOUR_OF_DAY);
		int q_r_minute = today.get(today.MINUTE);

		UserVo q_r_user = new UserVo();
		q_r_user.setU_id(u_id);

		QuestionReplyVo questionReplyVo = new QuestionReplyVo(q_r_user, q_id, q_r_content, q_r_year, q_r_month, q_r_day);

		questionReplyDao.insertQuestionReply(questionReplyVo);
		questionDao.updateQuestionReplyNumAdd(q_id);


		List<QuestionReplyVo> questionReplyList = questionReplyDao.getSelectAllofQuestionReply(q_id);

		if(!questionDao.getSelectQuestionUId(questionReplyVo.getQ_id()).equals(u_id)){
			String u_photo_url = userDao.getSelectUserPhoto(u_id);
			GCMSender gcmSender = new GCMSender();
			HashMap<String, String> gcmRegisterQuestion = userDao.getSelectGCMregisterQuestionReply(q_id);
			HashMap<String, T> resultHashMap = gcmSender.sendGCM(gcmRegisterQuestion.get("u_register_id"), "KIDS FARMER", "q_r", u_id, gcmRegisterQuestion.get("p_name")+"["+gcmRegisterQuestion.get("g_name")+"]", q_r_year, q_r_month, q_r_day, q_r_hour, q_r_minute, String.valueOf(q_id), u_photo_url, u_id+" ¥‘¿Ã "+gcmRegisterQuestion.get("p_name")+"["+gcmRegisterQuestion.get("g_name")+"]"+" ∏¿”ø° ¥Ò±€¿ª ≥≤∞ÂΩ¿¥œ¥Ÿ.", "");
			if(resultHashMap.get("result").equals("Success") && resultHashMap.containsKey("new_register_id")){
				userDao.updateRegisterId(gcmRegisterQuestion.get("u_register_id"), String.valueOf(resultHashMap.get("new_register_id")));
			}
		}

		return questionReplyList;
	}

	@RequestMapping(value = "/registerquestion", method = RequestMethod.GET)
	public @ResponseBody <T> List<QuestionVo> registerQuestion(@RequestParam("u_id") String u_id,
			@RequestParam("g_id") int g_id,
			@RequestParam("q_content") String q_content) {

		try{
			GregorianCalendar today = new GregorianCalendar();
			int q_year = today.get ( today.YEAR );
			int q_month = today.get ( today.MONTH ) + 1;
			int q_day = today.get ( today.DAY_OF_MONTH ); 
			int q_hour = today.get(today.HOUR_OF_DAY);
			int q_minute = today.get(today.MINUTE);

			UserVo q_user = new UserVo();
			q_user.setU_id(u_id);
			QuestionVo questionVo = new QuestionVo(q_user, g_id, q_content, q_year, q_month, q_day);

			questionDao.insertQeustion(questionVo);


			String g_u_id = gatheringDao.getSelectGatheringUId(g_id);
			if(!g_u_id.equals(u_id)){
				String u_photo_url = userDao.getSelectUserPhoto(u_id);
				GCMSender gcmSender = new GCMSender();
				HashMap<String, String> gcmRegisterQuestion = userDao.getSelectGCMregisterQuestion(g_id);
				HashMap<String, T> resultHashMap = gcmSender.sendGCM(gcmRegisterQuestion.get("u_register_id"), "KIDS FARMER","q", u_id, gcmRegisterQuestion.get("p_name")+"["+gcmRegisterQuestion.get("g_name")+"]", q_year, q_month, q_day, q_hour, q_minute, String.valueOf(questionVo.getQ_id()), u_photo_url, u_id+" ¥‘¿Ã "+gcmRegisterQuestion.get("p_name")+"["+gcmRegisterQuestion.get("g_name")+"]"+" ∏¿”ø° ¥Ò±€¿ª ≥≤∞ÂΩ¿¥œ¥Ÿ.", "");
				if(resultHashMap.get("result").equals("Success") && resultHashMap.containsKey("new_register_id")){
					userDao.updateRegisterId(gcmRegisterQuestion.get("u_register_id"), String.valueOf(resultHashMap.get("new_register_id")));
				}
			}
			List<QuestionVo> questionList = questionDao.getSelectAllofQuestion(g_id);

			return questionList;
		}catch(Exception e){
			return null;
		}
	}

	@RequestMapping(value = "/modifyquestionreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> modifyAfterwordReply(@RequestParam("q_id") int q_id, @RequestParam("q_r_id") int q_r_id,
			@RequestParam("q_r_content") String q_r_content) {
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			questionReplyDao.updateQuestionReply(q_r_id, q_r_content);
			List<QuestionReplyVo> questionReplyList = questionReplyDao.getSelectAllofQuestionReply(q_id);
			result.put("result", (T)"200");
			result.put("question_reply", (T)questionReplyList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}

	@RequestMapping(value = "/deletequestionreply", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deleteAfterwordreply(@RequestParam("q_id") int q_id, @RequestParam("q_r_id") int q_r_id) {
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			questionReplyDao.deleteQuestionReply(q_r_id);
			questionDao.updateQuestionReplyNumSub(q_id);
			List<QuestionReplyVo> questionReplyList = questionReplyDao.getSelectAllofQuestionReply(q_id);
			result.put("result", (T)"200");
			result.put("question_reply", (T)questionReplyList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}

		return result;
	}
	
	@RequestMapping(value = "/deletequestion", method = RequestMethod.GET)
	public @ResponseBody <T> HashMap<String, T> deletequestion(@RequestParam("q_id") int q_id){
		HashMap<String, T> result = new HashMap<String, T>();
		try{
			questionReplyDao.deleteQuestionReplyQId(q_id);
			int g_id = questionDao.getSelectGId(q_id);
			questionDao.deleteQuestion(q_id);
			List<QuestionVo> questionList = questionDao.getSelectAllofQuestion(g_id);
			result.put("result", (T)"200");
			result.put("question", (T)questionList);
		}catch(Exception e){
			result.put("result", (T)"500");
		}
		return result;
	}
}
