package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.web.entity.Form;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.service.UserInfoService;
@Controller
public class DeleteController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/deleteConfirm",method = RequestMethod.POST)
	public String delete(@ModelAttribute("command") Form form, Model model) {
		try {
			if(form.getId()==null) {
				model.addAttribute("msg", "必須項目を入力してください");
				return "delete";
			}else {
				Integer id = Integer.parseInt(form.getId());
				UserInfo success = userInfoService.findById(id);
				boolean isSuccess=success!=null;
				if(isSuccess) {
					session.setAttribute("deleteUser", success);
					return "deleteConfirm";
				}else {
					model.addAttribute("msg", "入力されたデータは存在しません");
					return "delete";
				}
			}

		}catch(Exception e){
			model.addAttribute("msg", "入力されたデータは存在しません");
			return "delete";

		}
	}

	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public String deleteConfirm(@ModelAttribute("command") Form form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			System.out.println(id);
			userInfoService.deleteById(id);
			session.removeAttribute("defo_id");
			return "deleteResult";
		}catch(Exception e){
			return "delete";
		}
	}

	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String deleteIn(@ModelAttribute("command") Form form, Model model) {
		return "delete";
	}
}
