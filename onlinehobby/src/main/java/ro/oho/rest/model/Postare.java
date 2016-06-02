package ro.oho.rest.model;

import ro.oho.rest.dao.UserDAO;
import ro.oho.rest.facadeDataBase.HobbyFacade;

public class Postare {
	private HobbyFacade hobbyFacade=new HobbyFacade();
	
	private int idHobby;
	private long idUser;
	private int idPost;
	private String Date_of_post;
	private String Mesaj;

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	public String getDate_of_post() {
		return Date_of_post;
	}

	public void setDate_of_post(String date_of_post) {
		Date_of_post = date_of_post;
	}

	public int getIdHobby() {
		return idHobby;
	}

	public void setIdHobby(int idHobby) {
		this.idHobby = idHobby;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long id) {
		this.idUser = id;
	}

	public String getMesaj() {
		return Mesaj;
	}

	public void setMesaj(String mesaj) {
		Mesaj = mesaj;
	}

}
