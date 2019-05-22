package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bll.RandomValidateCodeUtil;
import com.example.model.User;
import com.example.service.UserService;

@Controller
public class StaticController {

	@Autowired
	private UserService userService = new UserService();

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/centerhome")
	public String center() {
		return "center";
	}

	@GetMapping(value = "/center")
	public String centerById(@RequestParam("id") String id, @RequestParam("page") String page) {

		return "center";
	}

	@GetMapping(value = "/cal")
	public String cal() {
		return "calculator";
	}

	@ResponseBody
	@RequestMapping(value = "/cal/answer", method = RequestMethod.POST, headers = "Accept=application/json")
	public Map<String, Object> answer(@RequestParam String addnum, @RequestParam String addednum,
			@RequestParam String pattern, @RequestParam String already, @RequestParam String correct,
			@RequestParam String total_time, @RequestParam String resultans, @RequestParam String time,
			HttpSession session) {

		/*
		 * for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
		 * System.err.println(e.nextElement() + "-----" + session.getAttribute((String)
		 * e.nextElement())); }
		 */
		Map<String, Object> map = new HashMap<>();
		int[] result = new int[4];
		try {
			// 从session中获取随机数
			boolean isCorrect = false;
			switch (pattern) {
			case ("*"):
				isCorrect = (Integer.parseInt(addnum) * Integer.parseInt(addednum) == Integer.parseInt(resultans));
				break;
			case ("+"):
				isCorrect = (Integer.parseInt(addnum) + Integer.parseInt(addednum) == Integer.parseInt(resultans));
				break;
			case ("-"):
				isCorrect = (Integer.parseInt(addnum) - Integer.parseInt(addednum) == Integer.parseInt(resultans));
				break;
			case ("/"):
				isCorrect = (Integer.parseInt(addnum) / Integer.parseInt(addednum) == Integer.parseInt(resultans));
				break;
			}
			if (isCorrect)
				result[0] = 1;
			else
				result[0] = 0;
			result[1] = (int) (1 + Math.random() * (1000 - 1 + 1));
			result[2] = (int) (1 + Math.random() * (1000 - 1 + 1));
			result[3] = result[1] % 4;
			// String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
			// System.out.println(username);
			// System.out.println(password);
			// System.out.println(sbBuilder.toString());

			// System.out.println(sBuilder.toString());
			map.put("result0", String.valueOf(result[0]));
			map.put("result1", String.valueOf(result[1]));
			map.put("result2", String.valueOf(result[2]));
			map.put("result3", String.valueOf(result[3]));
			map.put("already", String.valueOf(Integer.parseInt(already) + 1));
			map.put("correct", String.valueOf(Integer.parseInt(correct) + result[0]));
			map.put("total_time", String.valueOf(Integer.parseInt(total_time) + Integer.parseInt(time)));

			return map;
		} catch (Exception e) {
			// logger.error("验证码校验失败", e);
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	/**
	 * 生成验证码
	 */
	@RequestMapping(value = "/login/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {
			// logger.error("获取验证码失败>>>> ", e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/login/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
	public int checkVerify(@RequestParam String verifyInput, @RequestParam String username,
			@RequestParam String password, HttpSession session) {
		try {
			// 从session中获取随机数
			String inputStr = verifyInput;
			String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
			// System.out.println(username);
			// System.out.println(password);
			if (random == null) {
				return 1;
			}
			if (!random.equals(inputStr)) {
				return 2;
			}
			List<User> list = userService.getByIdPassword(username, password);

			if (list.isEmpty())
				return 3;
			return 4;

		} catch (Exception e) {
			// logger.error("验证码校验失败", e);
			e.printStackTrace();
			return 5;
		}
	}
}