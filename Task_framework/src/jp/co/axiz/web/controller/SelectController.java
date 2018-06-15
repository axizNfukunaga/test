package jp.co.axiz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.Form;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserInfoService;
@Controller
public class SelectController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/select")
	public String select (@ModelAttribute("command") Form form, Model model) {

		return "select";
	}

	@RequestMapping(value="/selectResult", method=RequestMethod.POST)
	public String selectResult (@Validated @ModelAttribute("command") Form form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "エラー");
			return "select";
		}

		List<UserInfo> list = userInfoService.search(form.getId(), form.getName(), form.getTel());
		if (list == null || list.size() == 0) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		} else {
			model.addAttribute("user_info", list);
			return "selectResult";
		}
	}
}
