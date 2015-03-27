package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Message extends Model {
	@Id
	public Long id;
	public String tieude;
	public String noidung;
	public String nguoigui;
	@ManyToMany(mappedBy = "homthu")
	public List<UserAccount> nguoinhan;

	public Message() {
		super();
	}

	public String ngaygui;

	public Message(String tieude, String noidung, String nguoigui,
			String ngaygui) {
		super();
		this.tieude = tieude;
		this.noidung = noidung;
		this.nguoigui = nguoigui;
		this.ngaygui = ngaygui;
	}

	public static Finder<Long, Message> finder = new Finder<Long, Message>(
			Long.class, Message.class);

	public static Message findById(Long id) {
		return finder.byId(id);
	}

}
