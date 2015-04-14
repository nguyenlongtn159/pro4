package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Page;

import controllers.Application;

import play.data.format.Formats;
import play.mvc.PathBindable;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import scala.util.Either;

@Entity
public class UserAccount extends Model implements PathBindable<UserAccount> {
	@Id
	public Long id;
	@Constraints.Required
	public String email = "truongcan93@yahoo.com.vn";
	@Constraints.Required
	public String password = "12020400";
	public String description;
	@Constraints.Required
	public String name;
	@ManyToOne
	public Tag tag;
	public Long chucvu;
	public boolean dieukien = false;
	@ManyToMany
	public List<Message> homthu = new ArrayList<Message>();
	public List<Dot_BV_Detai> tiendo = new ArrayList<Dot_BV_Detai>();
	@ManyToOne
	public Detai detai;
	@ManyToOne
	public Detai chuyenmon;
	public Long cmon;
	@OneToMany(mappedBy = "duochuongdan")
	public List<UserAccount> huongdan = new ArrayList<UserAccount>();
	@ManyToOne
	public UserAccount duochuongdan;

	//them-14-4
	@Formats.DateTime(pattern = "dd/MM/yyyy")
	public Date date;
	//@Constraints.MaxLength(11)
	public int sdt;

	public String chucdanh;
	public String noicongtac;

	public UserAccount() {
		super();
	}

	public UserAccount(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public static Finder<Long, UserAccount> finder = new Finder<Long, UserAccount>(
			Long.class, UserAccount.class);

	public static UserAccount authenticate(String email, String password) {
		return finder.where().eq("email", email).eq("password", password)
				.findUnique();
	}

	public static List<UserAccount> findAll() {
		return finder.all();
	}

	public static UserAccount findByName(String name) {
		return finder.where().eq("name", name).findUnique();
	}

	public static UserAccount findByEmail(String email) {
		return finder.where().eq("email", email).findUnique();
	}

	@Override
	public UserAccount bind(String key, String value) {
		// TODO Auto-generated method stub
		return findByName(value);
	}

	@Override
	public String unbind(String arg0) {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String javascriptUnbind() {
		// TODO Auto-generated method stub
		return name;
	}

	public static Page<UserAccount> find(int page) {
		return finder.where().orderBy("id asc").findPagingList(10)
				.setFetchAhead(false).getPage(page);

	}

	public static UserAccount findById(Long id) {
		return finder.byId(id);
	}

	public static Page<UserAccount> find_gv(int page, Long mChuyenmon) {
		return finder.where().eq("cmon", mChuyenmon).orderBy("id asc")
				.findPagingList(10).setFetchAhead(false).getPage(page);
	}

	public boolean getCheck_dangky() {
		return Application.dangky_check;
	}

	public static List<Message> findmsg(UserAccount user) {
		return user.homthu;
	}

	public boolean getCheck() {
		return Application.dangky_check;
	}

}
