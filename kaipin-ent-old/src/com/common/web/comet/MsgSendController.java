package com.common.web.comet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.util.JsonUtil;

/**
 * 手机端消息推送给客户端
 * 
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("msg")
public class MsgSendController {
	
	Logger log = Logger.getLogger(MsgSendController.class.getName());

	@RequestMapping(value = "/sendMsgs")
	@ResponseBody
	public Map<String, Object> init(HttpServletRequest request, String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CometEngine engine = CometContext.getInstance().getEngine();
			// 参数的意思：通过什么频道（CHANNEL1）发送数据给前台
			String userIds[] = { "U3" };
			userIds.hashCode();
			map.put("type", Constant.VALUE_TWO);// 消息类型 1公有 2私有
			map.put("user_ids", userIds);
			map.put("company_id", "Qy3");
			map.put("msg_type", Constant.VALUE_FOUR); // 和推送消息类型保存一致
			engine.sendToAll(Constant.CHANNEL_MSG_INTERVIEW, JsonUtil.toJson(map));
			// 0 代表成功
			// -1代表失败
			map.clear();
			map.put(Constant.CODE, 0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put(Constant.CODE, -1);
			log.error(e);
			return map;
		}

	}
}
