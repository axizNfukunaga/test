package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.Form;
import jp.co.axiz.web.service.AdminService;

@Controller
public class AuthController {

	@Autowired
	private AdminService adminService;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String toLogin(@ModelAttribute("login") Form form, BindingResult bindingResult, Model model) {
		return "login";
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("login") Form form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		String id = form.getId();
		String pass = form.getPass();
		Admin admin = adminService.findById(id,pass);

		if(admin==null||id.equals("") || pass.equals("")) {
			model.addAttribute("msg", "IDまたはPASSが間違っています");
			return "login";
		}else {
			session.setAttribute("controller", admin);
			return "menu";
		}
	}

	@RequestMapping("/logout")
	public String logout (Model model) {

		session.invalidate();

		return "logout";
	}
}
