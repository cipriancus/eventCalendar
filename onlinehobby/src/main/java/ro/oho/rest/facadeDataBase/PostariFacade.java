package ro.oho.rest.facadeDataBase;

import java.sql.SQLException;
import java.util.List;

import ro.oho.rest.dao.PostareDAO;
import ro.oho.rest.model.Postare;
import ro.oho.rest.model.User;

public class PostariFacade {

	private PostareDAO postareRepository=new PostareDAO();
	
	public List<Postare> getAllPostariForId(long id) {
		return postareRepository.getAllPostariForId(id);
	}
	
	public List<Postare> getAllUserPostariForId(long id) {
		return postareRepository.getAllUserPostariForId(id);
	}
	
	public boolean postInHobby(User user,String hobbyName,String message)throws SQLException{
		return postareRepository.postInHobby(user.getIdUser(), hobbyName, message);
	}
	
	public List<Postare> getAllHobbyPost(int id){
		return postareRepository.getAllHobbyPost(id);
	}
}
