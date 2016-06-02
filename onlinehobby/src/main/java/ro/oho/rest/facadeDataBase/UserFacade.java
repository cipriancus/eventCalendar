package ro.oho.rest.facadeDataBase;

import java.sql.SQLException;
import java.util.List;

import ro.oho.rest.dao.UserDAO;
import ro.oho.rest.model.User;

public class UserFacade {

	private static final UserDAO userRepository = new UserDAO();

	public User getUserById(long id) {
		return userRepository.getUserById(id);
	}

	public boolean adminUser(String username)throws SQLException {
		return userRepository.adminUser(username);
	}
	
	public boolean deleteUser(String username) throws SQLException{
		return userRepository.deleteUser(username);
	}

	public boolean updateUser(User user)throws SQLException{
		return userRepository.updateUser(user);
	}
	
	public boolean createUser(User user) throws SQLException{
		return userRepository.createUser(user);
	}

	public boolean loginUser(long idUser){
		if (userRepository.login(idUser) != false) {
			
			User user = getUserById(idUser);
			if (user.getIdUser()==idUser)
				return true;
		}
		return false;
	}

	public List<String> getUserPage(int page) throws SQLException{
		return userRepository.getUserPage(page);
	}
	
	public boolean generateCVS()throws SQLException{
		return userRepository.generateCVS();
	}
}
