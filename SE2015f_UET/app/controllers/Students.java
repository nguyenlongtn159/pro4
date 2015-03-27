package controllers;

import java.util.List;

import models.Detai;
import models.Message;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import com.avaje.ebean.Page;

@Security.Authenticated(Sv_Security.class)
public class Students extends Controller {
	public static final Form<UserAccount> userForm = Form
			.form(UserAccount.class);

	public static Result details(UserAccount user) {
		if (user == null) {
			return notFound("không tìn thấy 1" + user.name);
		}
		return ok(views.html.svdetails.render(user));
	}

	public static Result show(UserAccount name, UserAccount name1) {
		if (name1 == null)
			return notFound("Không tìm thấy 2");
		Form<UserAccount> filldedForm = userForm.fill(name1);
		return ok(views.html.svshow.render(filldedForm, name));
	}

	public static Result save(UserAccount name) {
		Form<UserAccount> boundForm = userForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(views.html.details.render(boundForm, name));
		}
		UserAccount account = boundForm.get();
		if (account.id == null) {

		}
		// account.save();
		else {
			System.out.println("chan");
			UserAccount model = UserAccount.findById(account.id);
			model.name = account.name;
			model.description = account.description;
			model.update();
		}

		flash("succes", String.format("Successfully added list"));
		return redirect(routes.Students.details(name));
	}

	public static Result newAccount(UserAccount name) {
		return ok(views.html.details.render(userForm, name));
	}

	public static Result list(Integer page, UserAccount user) {
		if (!user.dieukien)
			return notFound("Bạn chưa đủ điều kiện đăng ký đề tài");
		if (!Application.dangky_check && user.detai != null
				&& user.duochuongdan != null) {
			List<Message> msg = UserAccount.findmsg(user);
			if(msg.isEmpty()) return notFound("12345");
			return ok(views.html.sv_tiendo.render(msg, user));
		}
		if (!Application.dangky_check)
			return notFound("Đã ngoài thời gian đăng ký và bảo vệ đề tài!");
		Page<Detai> hodle = Detai.find(page);
		return ok(views.html.sv_detai_all.render(hodle, user));
		// return TODO;
	}

	public static Result Dangky(UserAccount user, Detai mDetai) {
		user.detai = mDetai;
		user.duochuongdan = null;
		user.update();
		// return redirect(routes.Students.list(0, user));
		return ok();
	}

	public static Result gv_list(Integer page, UserAccount user, Long mChuyenmon) {
		Page<UserAccount> hodle = UserAccount.find_gv(page, mChuyenmon);
		return ok(views.html.sv_gv_all.render(hodle, user, mChuyenmon));
		// return ok();
	}

	public static Result Dangky_gv(UserAccount user, UserAccount giangvien,
			Long mChuyenmon) {
		if (user.detai.id == giangvien.chuyenmon.id) {
			user.duochuongdan = giangvien;
			user.update();
		}
		// return redirect(routes.Students.gv_list(0,user,mChuyenmon));
		return ok();
	}

	// Hàm này bỏ
	public static Result modelsList(UserAccount name) {
		List<Message> msg = UserAccount.findmsg(name);
		return ok();
	}

}
