package com.example.demo.controller;

import java.util.List;

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