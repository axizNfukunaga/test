package jp.co.axiz.web.controller;

import javax.servlet.http.HttpSession;

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
public class UpdateController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String returnUpdate(@ModelAttribute("command") Form form, Model model) {
		return "update";
	}

	@RequestMapping(value="/backUpdateInput",method = RequestMethod.GET)
	public String returnUpdateInput(@ModelAttribute("command") Form form, Model model) {
		return "updateInput";
	}


	@RequestMapping(value="/updateInput",method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute("command") Form form, BindingResult bindingResult, Model model) {
		try {
			if (form.getId()==null) {
				model.addAttribute("msg","必須項目を入力してください");
				return "update";
			}else {
				Integer id  = Integer.parseInt(form.getId());
				UserInfo user = userInfoService.findById(id);
				if(user==null) {
					model.addAttribute("msg","入力されたデータは存在しません");
					return "update";
				}else {
					session.setAttribute("beforeUser", user);
					return "updateInput";
				}
			}
		}catch(Exception e) {
			model.addAttribute("msg","必須項目を入力してください");
			return "update";
		}

	}

	@RequestMapping(value="/updateConfirm",method = RequestMethod.POST)
	public String updateConfirm(@Validated @ModelAttribute("command") Form form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "updateInput";
		}
		String newName = form.getNewName();
		String newTel = form.getNewTel();
		String newPass = form.getNewPass();

		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");

		Integer id = beforeUser.getUserId();

		UserInfo newUser = new UserInfo(id, newName, newTel, newPass);

		session.setAttribute("NewUser", newUser);
		session.setAttribute("defo_id", id);

		if(beforeUser.getPassword().equals(newUser.getPassword())) {
			session.setAttribute("Pass", beforeUser.getPassword());

		}else {
			session.setAttribute("Pass", "");
		}

		if(newName.equals(beforeUser.getUserName()) && newTel.equals(beforeUser.getTelephone()) && newPass.equals(beforeUser.getPassword())) {
			model.addAttribute("msg", "1項目以上変更してください。");

			return "updateInput";
		}

		return "updateConfirm";
	}

	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updateResult(@ModelAttribute("command") Form form, Model model) {
		String rePass = form.getRePass();
		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");
		UserInfo newUser = (UserInfo)session.getAttribute("NewUser");

		String newName =newUser.getUserName();
		String newTel = newUser.getTelephone();
		String newPass = newUser.getPassword();
		if(!(newPass.equals(rePass))){
			model.addAttribute("msg", "前画面で入力したパスワードと一致しません");
			return "updateConfirm";
		}
		else if(newName != null && newTel != null && newPass != null){

				if(!(newPass.equals(beforeUser.getPassword())) && !("".equals(newPass))){
					if(newPass.equals(rePass)) {
						userInfoService.updatePass(newUser);
					}
				}
				if(!(newName.equals(beforeUser.getUserName())) && !(newName.equals(""))) {
					userInfoService.updateName(newUser);
				}

				if(!(newTel.equals(beforeUser.getTelephone())) && !(newTel.equals(""))){
					userInfoService.updateTel(newUser);
				}

				return "updateResult";
		}else {
			model.addAttribute("msg", "前画面で入力したパスワードと一致しません");

			return "updateConfirm";
		}


	}

}
