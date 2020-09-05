package com.redsocial.web.app.DAO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redsocial.web.app.Models.User;
import com.redsocial.web.app.Services.IUserService;

@Service
public class UserDAO implements IUserService {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	
	@Transactional
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub

		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("CREATE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();
		
		Map.put("PUSER_NICKNAME", user.getUserNickname());
		Map.put("PUSER_MAIL", user.getUserMail());
		Map.put("PUSER_PASSWORD", user.getUserPassword());
		
		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);
		
		jdbcCall.execute(src);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPDATE_USER");
		Map<String, Object> Map = new HashMap<String, Object>();
		
		Map.put("PUSER_NAME", user.getUserName());
		Map.put("PUSER_NICKNAME", user.getUserNickname());
		Map.put("PUSER_MAIL", user.getUserMail());
		Map.put("PUSER_PASSWORD", user.getUserPassword());
		Map.put("PUSER_BIRTHDATE", user.getUserBirthDate());
		Map.put("PUSER_GENDER", user.getUserGender());
		Map.put("PUSER_ID", user.getIdUser());

		
		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);
		
		jdbcCall.execute(src);
		
	}

	@Override
	public void deleteUser(Integer idUser) {
		// TODO Auto-generated method stub
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("DELETE_USER");
		SqlParameterSource src = new MapSqlParameterSource().addValue("PUSER_ID", idUser);
		
		jdbcCall.execute(src);

	}

}
