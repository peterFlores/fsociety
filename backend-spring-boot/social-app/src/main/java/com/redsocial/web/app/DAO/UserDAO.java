package com.redsocial.web.app.DAO;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.redsocial.web.app.Services.IUserService;

@Service
public class UserDAO implements IUserService {

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

		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		jdbcCall.execute(src);

	}

	@Override
	public void updateUser(User user) throws Exception {

		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPDATE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PUSER_NAME", user.getUserName());
		Map.put("PUSER_NICKNAME", user.getUserNickname());
		Map.put("PUSER_MAIL", user.getUserMail());
		Map.put("PUSER_PASSWORD", user.getUserPassword());
		Map.put("PUSER_BIRTHDATE", user.getUserBirthDate());
		Map.put("PUSER_GENDER", user.getUserGender());
		Map.put("PUSER_ID", user.getIdUser());

		// VALIDATE IF EMAIL OR NICKNAME EXISTS
		String sqlEmail = "SELECT count(*) FROM USERS WHERE USER_MAIL = ? ";
		String sqlNickname = "SELECT count(*) FROM USERS WHERE USER_NICKNAME = ? ";

		if (jdbcTemplate.queryForObject(sqlEmail, new Object[] { user.getUserMail() }, Integer.class) > 0) {
			throw new RuntimeException("EMAIL ALREADY EXISTS");
		}

		if (jdbcTemplate.queryForObject(sqlNickname, new Object[] { user.getUserNickname() }, Integer.class) > 0) {
			throw new RuntimeException("NICKNAME ALREADY EXISTS");
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

		List<User> listUser = jdbcTemplate.query(sql,new RowMapper<User>() {
			
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
	public List<User> findByUserId(Integer idUser) {
		String sql = "SELECT * FROM USERS WHERE USER_ID = ?";

		List<User> listUser = jdbcTemplate.query(sql,new Object[] {idUser},new RowMapper<User>() {
			
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
	public List<User> findByUserMail(String email) {
		String sql = "SELECT * FROM USERS WHERE USER_MAIL = ?";

		List<User> listUser = jdbcTemplate.query(sql,new Object[] {email},new RowMapper<User>() {
			
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
	public List<User> findByUserNickname(String nickname) {
		String sql = "SELECT * FROM USERS WHERE USER_NICKNAME LIKE  ?";
		


		List<User> listUser = jdbcTemplate.query(sql,new Object[] {nickname},new RowMapper<User>() {
			
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
	public List<User> findByUserName(String userName) {
		String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";

		List<User> listUser = jdbcTemplate.query(sql,new Object[] {userName},new RowMapper<User>() {
			
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
	public void updatePicture(MultipartFile picture, Long idUser) throws IllegalStateException, IOException {

		String route = "/var/www/html/Images/" + picture.getOriginalFilename();    
		
		User user = new User();
		
		user.setIdUser(idUser);
		user.setUserImage(route);
		
			picture.transferTo(new File(route));
			
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPDATE_PICTURE_USER");
			Map<String, Object> Map = new HashMap<String, Object>();
			
			Map.put("PUSER_ID", user.getIdUser());
			Map.put("PUSER_IMAGE_PATH", user.getUserImage());
	
			SqlParameterSource src = new MapSqlParameterSource().addValues(Map);
			jdbcCall.execute(src);
			
	}


}
