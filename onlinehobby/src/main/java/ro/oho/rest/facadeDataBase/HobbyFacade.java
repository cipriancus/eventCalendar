package ro.oho.rest.facadeDataBase;

import java.sql.SQLException;
import java.util.List;

import ro.oho.rest.dao.HobbyDAO;
import ro.oho.rest.model.Hobby;
import ro.oho.rest.model.User;

public class HobbyFacade {
	private HobbyDAO hobbyDAO=new HobbyDAO();
	
	public String getHobbyNameForId(int id){
		return hobbyDAO.getHobbyForId(id).getHobbyName();
	}
	
	public Hobby getHobbyForId(int id){
		return hobbyDAO.getHobbyForId(id);
	}
	
	public int getHobbyIdForName(String name){
		return hobbyDAO.getHobbyForName(name).getIdHobby();
	}
	
	public List<Hobby> getAllUserHobby(long id){
		return hobbyDAO.getAllUserHobby(id);
	}
	
	public List<Hobby> getRecommendation(int page,User user)throws SQLException{
		return hobbyDAO.getRecommendation(page,user.getIdUser());
	}
	
	public boolean addUserToHobby(User user,int idHobby) throws SQLException{
		return hobbyDAO.addUserToHobby(user.getIdUser(),idHobby);
	}
	
	public List<User> getAllUsersForHobby(int id) throws SQLException{
		return hobbyDAO.getAllUsersForHobby(id);
	}
	
	public List<String> getAllHobbies() throws SQLException {
		return hobbyDAO.getAllHobbies();
	}
}
