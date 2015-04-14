package controllers;

import com.avaje.ebean.Page;
import models.Message;
import models.Tag;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

@Security.Authenticated(Gv_Security.class)
public class Teachers extends Controller {


	public static final Form<UserAccount> userForm = Form
			.form(UserAccount.class);
	public static final Form<Message> msgForm = Form.form(Message.class);


	public static Result details(UserAccount user) {
		if (user == null) {
			return notFound("không tìn thấy 1" + user.name);
		}
		return ok(views.html.gvdetails.render(user));
	}


	public static Result list(Integer page, UserAccount user) {
		Page<UserAccount> hodle = UserAccount.find(page);
		return ok(views.html.hiddenlist.render(hodle, user));
	}

	public static Result reSearch() {
		return ok();
	}

	public static Result show(UserAccount name, UserAccount name1) {
		if (name1 == null)
			return notFound("Không tìm thấy 2");
		Form<UserAccount> filldedForm = userForm.fill(name1);
		return ok(views.html.gvshow.render(filldedForm, name));
	}

	public static Result save(UserAccount name) {
		Form<UserAccount> boundForm = userForm.bindFromRequest();
		if (boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(views.html.gvshow.render(boundForm, name));

		}
		else{
			UserAccount account = boundForm.get();


			UserAccount modles = UserAccount.findById(account.id);
			modles.name = account.name;
			modles.date = account.date;
			modles.sdt = account.sdt;
			modles.chucdanh = account.chucdanh;
			modles.noicongtac = account.noicongtac;
			modles.description = account.description;
			modles.update();
		}
		flash("succes", String.format("Successfully added list"));
		return redirect(routes.Teachers.list(0, name));

	}
}
