package ro.oho.rest.dao;

import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ro.oho.rest.model.ConnectionHelperClass;
import ro.oho.rest.model.User;

public class UserDAO {

	private static final String GET_USER_BY_ID = "SELECT * FROM usersoho where idUser=?";

	private static final String INSERT_USER = "{call userskills.addUser(?,?,?,?,?,?,?,?)}";

	private static final String DELETE_USER = "{call adminskills.deleteUser(?)}";

	private static final String ADMIN_USER = "{call adminskills.setGrade(?,'Administrator')}";

	private static final String LOGIN_USER = "{call userSkills.loginUser(?)}";

	private static final String UPDATE_USER = "{call userSkills.updateUser(?,?,?,?,?,?,?,?)}";

	private static final String GET_USER = "{call paginare(?, ?)}";

	private static final String GENERATE_CVS = "{call generate()}";

	public boolean generateCVS() throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(GENERATE_CVS);
		cstmt.execute();

		return true;
	}

	public boolean createUser(User user) throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(INSERT_USER);
		//p_idUser number, p_nameUser varchar2, p_surnameUser varchar2,
		//p_dataBirth varchar2, p_email varchar2,p_authToken varchar2,p_authSite varchar2)
		cstmt.setLong(1, user.getIdUser());
		cstmt.setString(2, user.getNume());
		cstmt.setString(3, user.getPrenume());
		cstmt.setString(4, user.getDataNasterii());
		cstmt.setString(5, user.getEmail());
		cstmt.setString(6, user.getAuthToken());
		cstmt.setString(7, user.getAuthSite());
		cstmt.setString(8, user.getPicture());
		cstmt.execute();

		return true;
	}

	public List<String> getUserPage(int page) throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(GET_USER);
		cstmt.setInt(1, page);
		cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		cstmt.execute();

		ResultSet rs = (ResultSet) cstmt.getObject(2);
		List<String> list = new ArrayList<String>();

		while (rs.next()) {
			String user = rs.getInt("idUser") + " " + rs.getString("nameuser") + " " + rs.getString("surnameuser") + " "
					+ rs.getDate("date_of_birth") + " " + rs.getString("email") + " " + rs.getString("username") + " "
					+ rs.getInt("idgrad");
			list.add(user);
		}
		 

		return list;
	}

	public boolean adminUser(String username) throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(ADMIN_USER);
		cstmt.setString(1, username);
		cstmt.execute();
		 

		return true;
	}

	public boolean login(Long idUser) {
		try {
			Connection con = ConnectionHelperClass.getOracleConnection();
			CallableStatement cstmt = con.prepareCall(LOGIN_USER);
			cstmt.setLong(1, idUser);
			cstmt.execute();
			 

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUserById(long id) {
		Connection con = ConnectionHelperClass.getOracleConnection();

		User user = new User();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(GET_USER_BY_ID);
			prepareStatement.setLong(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();

			user.setIdUser(resultSet.getLong("idUser"));
			user.setNume(resultSet.getString("nameuser"));
			user.setPrenume(resultSet.getString("surnameuser"));
			user.setDataNasterii(resultSet.getString("date_of_birth"));
			user.setEmail(resultSet.getString("email"));
			user.setAuthToken(resultSet.getString("authtoken"));
			user.setAuthSite(resultSet.getString("authSite"));
			user.setPicture(resultSet.getString("photourl"));
			user.setGrad(resultSet.getInt("idgrad"));
			 

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(User userEntity) throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(UPDATE_USER);
		cstmt.setLong(1, userEntity.getIdUser());
		cstmt.setString(2, userEntity.getNume());
		cstmt.setString(3, userEntity.getPrenume());
		cstmt.setString(4, userEntity.getDataNasterii());
		cstmt.setString(5, userEntity.getEmail());
		cstmt.setString(6, userEntity.getAuthToken());
		cstmt.setString(7, userEntity.getAuthSite());
		cstmt.setString(8, userEntity.getPicture());
		cstmt.execute();
		 

		return true;
	}

	public boolean deleteUser(String username) throws SQLException {
		Connection con = ConnectionHelperClass.getOracleConnection();
		CallableStatement cstmt = con.prepareCall(DELETE_USER);
		cstmt.setString(1, username);
		cstmt.execute();
		 

		return true;
	}

}
