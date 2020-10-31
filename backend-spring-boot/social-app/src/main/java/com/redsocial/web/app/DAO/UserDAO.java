package com.redsocial.web.app.DAO;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.redsocial.web.app.Models.User;
import com.redsocial.web.app.Models.UserJPA;
import com.redsocial.web.app.Services.IUserService;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class UserDAO implements IUserService {
	
	@Autowired
	private IUserDAO userDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Transactional
	@Override
	public void createUser(User user) throws Exception {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("CREATE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PUSER_NICKNAME", user.getUserNickname());
		Map.put("PUSER_MAIL", user.getUserMail());
		Map.put("PUSER_PASSWORD", user.getUserPassword());

		// VALIDATE IF EMAIL OR NICKNAME EXISTS
		String sqlEmail = "SELECT count(*) FROM USERS WHERE USER_MAIL = ? ";
		String sqlNickname = "SELECT count(*) FROM USERS WHERE USER_NICKNAME = ? ";

		if (jdbcTemplate.queryForObject(sqlEmail, new Object[] { user.getUserMail() }, Integer.class) > 0) {
			throw new RuntimeException("EMAIL ALREADY EXISTS");
		}

		if (jdbcTemplate.queryForObject(sqlNickname, new Object[] { user.getUserNickname() }, Integer.class) > 0) {
			throw new RuntimeException("NICKNAME ALREADY EXISTS");
		}
		
		if (StringUtils.isBlank(user.getUserNickname())) {
			throw new Exception("NICKNAME IS A REQUIRED");
		}
		
		if (StringUtils.isBlank(user.getUserMail())) {
			throw new Exception("MAIL IS A REQUIRED");
		}

		if (StringUtils.isBlank(user.getUserPassword())) {
			throw new Exception("PASSWORD IS A REQUIRED");
		}
		
	
		final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	    Matcher matcher = VALID_EMAIL.matcher(user.getUserMail());
	    
	    if(matcher.find() == false) {
	    	throw new Exception("MAIL INVALID");
	    }
		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		jdbcCall.execute(src);

	}

	@Override
	public void updateUser(User user) throws Exception {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPDATE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PUSER_NAME", user.getUserName());
		Map.put("PUSER_NICKNAME", user.getUserNickname());
		Map.put("PUSER_BIRTHDATE", user.getUserBirthDate());
		Map.put("PUSER_GENDER", user.getUserGender());
		Map.put("PUSER_ID", user.getIdUser());

		// VALIDATE IF EMAIL OR NICKNAME EXISTS
		String sqlNickname = "SELECT count(*) FROM USERS WHERE USER_NICKNAME = ? ";


		if (jdbcTemplate.queryForObject(sqlNickname, new Object[] { user.getUserNickname() }, Integer.class) > 0) {
			throw new RuntimeException("NICKNAME ALREADY EXISTS");
		}
		
		if (StringUtils.isBlank(user.getUserName())) {
			throw new Exception("NAME IS A REQUIRED");
		}
		
		if (StringUtils.isBlank(user.getUserNickname())) {
			throw new Exception("NICKNAME IS A REQUIRED");
		}
		

		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		jdbcCall.execute(src);

	}

	@Override
	public void deleteUser(Integer idUser) {
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("DELETE_USER");
		SqlParameterSource src = new MapSqlParameterSource().addValue("PUSER_ID", idUser);

		jdbcCall.execute(src);

	}

	@Transactional(readOnly = true)
	@Override
	public List<User> listUsers() {

		String sql = "SELECT * FROM USERS WHERE USER_ROLE = 'SOCIAL' ";

		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
					
				user.setUserImage(rs.getString("USER_IMAGE_PATH"));
				user.setIdUser(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserNickname(rs.getString("USER_NICKNAME"));
				user.setUserMail(rs.getString("USER_MAIL"));
				user.setUserBirthDate(rs.getDate("USER_BIRTHDATE"));
				user.setUserGender(rs.getString("USER_GENDER"));
				user.setUserCreatedAt(rs.getDate("USER_CREATED_AT"));
				user.setUserStatus(rs.getString("USER_STATUS"));
				return user;
			}
		});

		return listUser;

	}



	@Override
	public List<User> findBy(String searchMethod, String searchData){
		
		String option = searchMethod;
		String sql = null;
		
		switch(option) 
        { 
            case "byName": 
        	 sql = "SELECT * FROM USERS WHERE USER_NAME LIKE '%" + searchData + "%' AND USER_ROLE = 'SOCIAL'";
            	break;

            case "byNickname": 
        	sql = "SELECT * FROM USERS WHERE USER_NICKNAME LIKE  '%" + searchData + "%' AND USER_ROLE = 'SOCIAL'";

                break; 
            case "byMail": 
        	 sql = "SELECT * FROM USERS WHERE USER_MAIL LIKE  '%" + searchData + "%' AND USER_ROLE = 'SOCIAL'";

                break;
                
            case "byId":
        	 sql = "SELECT * FROM USERS WHERE USER_ID LIKE  '%" + searchData + "%' AND USER_ROLE = 'SOCIAL'";

            	break;
            default: 
                System.out.println("NOT A SEARCH METHOD"); 
                
        } 
		

		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setUserImage(rs.getString("USER_IMAGE_PATH"));
				user.setIdUser(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserNickname(rs.getString("USER_NICKNAME"));
				user.setUserMail(rs.getString("USER_MAIL"));
				user.setUserBirthDate(rs.getDate("USER_BIRTHDATE"));
				user.setUserGender(rs.getString("USER_GENDER"));
				user.setUserCreatedAt(rs.getDate("USER_CREATED_AT"));
				user.setUserStatus(rs.getString("USER_STATUS"));
				return user;

			}
		});
		
		if (listUser.isEmpty()) {
			throw new RuntimeException("USER NOT FOUND.");
		}

		return listUser;
	}
	
	
	
	

	// Dont delete, implemented on oauth-service

	@Override
	public List<User> findByUserMail(String email) {
		String sql = "SELECT * FROM USERS WHERE USER_MAIL = ?";

		List<User> listUser = jdbcTemplate.query(sql, new Object[] { email }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setIdUser(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserNickname(rs.getString("USER_NICKNAME"));
				user.setUserMail(rs.getString("USER_MAIL"));
				user.setUserBirthDate(rs.getDate("USER_BIRTHDATE"));
				user.setUserGender(rs.getString("USER_GENDER"));
				user.setUserCreatedAt(rs.getDate("USER_CREATED_AT"));
				user.setUserStatus(rs.getString("USER_STATUS"));
				user.setUserImage(rs.getString("USER_IMAGE_PATH"));
				user.setUserPassword(rs.getString("USER_PASSWORD"));
				user.setUserRole(rs.getString("USER_ROLE"));

				return user;
			}
		});

		return listUser;
	}
	@Override
	public UserJPA findByUserId(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException("NO USER FOUND"));
	}

	@Override
	public void updatePicture(MultipartFile picture, Long idUser) throws IllegalStateException, IOException {

		String saveRoute = "/var/www/html/Images/Profile/";
		String route = "http://3.22.230.92/Images/Profile/";
		User user = new User();

		File profilepic = new File(saveRoute + picture.getOriginalFilename());

		picture.transferTo(profilepic);

		user.setIdUser(idUser);
		user.setUserImage(route + picture.getOriginalFilename());


		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPDATE_PICTURE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PUSER_ID", user.getIdUser());
		Map.put("PUSER_IMAGE_PATH", user.getUserImage());

		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		
		jdbcCall.execute(src);

	}

	@Override
	public List<User> findByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listTop5() {
		// TODO Auto-generated method stub
		String sql = "CALL TOP_5_VISIT()";

		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setIdUser(rs.getLong("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserImage(rs.getString("USER_IMAGE_PATH"));
				user.setUserCreatedAt(rs.getDate("USER_CREATED_AT"));
				user.setUserStatus(rs.getString("USER_STATUS"));


				return user;
			}
		});

		return listUser;

}
}
