package controllers;

import models.UserAccount;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Gv_Security.class)
public class Teachers extends Controller{
	
	public static Result details(UserAccount user){
		if(user==null){
			return notFound("không tìn thấy "+user.name);
		}
		return ok();
	}

}
