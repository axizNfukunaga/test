package jp.co.axiz.web.controller;

import java.util.List;

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
public class InsertController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/insert",method = RequestMethod.GET)
	public String insertIn(@ModelAttribute("command") Form form,Model model) {		//メニューで登録押したらそのページに飛ばすよ
		return "insert";
	}

	@RequestMapping(value="/insertConfirm",method = RequestMethod.POST)
	public String insert(@ModelAttribute("command") Form form,Model model) {		//登録画面で入力後の処理だよ
		if((form.getName()==null || form.getTel()==null ||form.getPass()==null)||"".equals(form.getName())||"".equals(form.getTel())||"".equals(form.getPass())){
			model.addAttribute("msg", "必須項目を入力してください");
			return "insert";
		}
		session.setAttribute("insertname", form.getName());
		session.setAttribute("inserttel", form.getTel());
		session.setAttribute("insertpass", form.getPass());		//確認画面で使うからセッションいれるよ
		return "insertConfirm";
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insertResult(@ModelAttribute("command") Form form,Model model) {		//確認したよ
		if(form.getRePass().equals(session.getAttribute("insertpass"))) {//パスあってたら
			userInfoService.register((String)session.getAttribute("insertname"),(String)session.getAttribute("inserttel"),(String)session.getAttribute("insertpass"));	//登録するよ
			List<UserInfo> user = userInfoService.findMaxId();
			UserInfo uI = user.get(0);
			Integer maxId = uI.getUserId();

			session.setAttribute("defo_id", maxId);		//検索時に初期表示させるために入れるよ
			return "insertResult";
		}else {
			model.addAttribute("msg", "前画面で入力したパスワードと一致しません");
			return "insertConfirm";
		}
	}
}
