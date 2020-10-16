package com.redsocial.web.app.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redsocial.web.app.Models.Analitics;
import com.redsocial.web.app.Services.IAnaliticsService;

@Service
public class AnaliticsDAO implements IAnaliticsService {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Analitics> mainPageVisits() {
		
		String sql = "SELECT * FROM V_VIEWS_PER_DAY";
		
		
		List<Analitics> analiticsData = jdbcTemplate.query(sql, new RowMapper<Analitics>() {

			@Override
			public Analitics mapRow(ResultSet rs, int rowNum) throws SQLException {
				Analitics analitics = new Analitics();
				analitics.setTitleAnalitic("TOTAL DE VISITAS Y REGISTRADOS");		
				analitics.setDateAnalitics(rs.getDate("FECHA"));
				analitics.setVisitsDay(rs.getLong("VISITAS_DIARIAS"));
				analitics.setRegistredDay(rs.getLong("USUARIOS_CREADOS"));

				return analitics;
			}
		
		});
		
		return analiticsData;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public List<Analitics> top5Post() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM V_POSTS_TOP5";
		
		
		List<Analitics> analiticsData = jdbcTemplate.query(sql, new RowMapper<Analitics>() {

			@Override
			public Analitics mapRow(ResultSet rs, int rowNum) throws SQLException {
				Analitics analitics = new Analitics();
				analitics.setTitleAnalitic("TOP 5 USUARIOS CON MAS PUBLICACIONES.");		
				analitics.setIdUser(rs.getLong("USER_ID"));
				analitics.setUserNickname(rs.getString("USER_NICKNAME"));
				analitics.setNumberPosts(rs.getLong("POSTS_COUNT"));
				return analitics;
			}
		
		});
		
		return analiticsData;
	
	}
	
	
	@Override
	public List<Analitics> genderRegistred() {
		String sql = "SELECT * FROM V_USERS_BY_GENDER";
		
		
		List<Analitics> analiticsData = jdbcTemplate.query(sql, new RowMapper<Analitics>() {

			@Override
			public Analitics mapRow(ResultSet rs, int rowNum) throws SQLException {
				Analitics analitics = new Analitics();
				
				analitics.setTitleAnalitic("GENEROS DE USUARIOS REGISTRADOS.");				
				analitics.setGenderAnalitic(rs.getString("GENDER"));
				analitics.setNumberGender(rs.getLong("USERS_COUNT"));
				return analitics;
			}
		
		});
		
		return analiticsData;
	
	}
	
	@Override
	public List<Analitics> top5week() {
		String sql = "SELECT * FROM V_TOP5_VISITS_WEEKLY";
		
		
		List<Analitics> analiticsData = jdbcTemplate.query(sql, new RowMapper<Analitics>() {

			@Override
			public Analitics mapRow(ResultSet rs, int rowNum) throws SQLException {
				Analitics analitics = new Analitics();
				analitics.setTitleAnalitic("TOP 5 VISITADOS EN LA SEMANA");				
				analitics.setUserName(rs.getString("USER_NAME"));
				analitics.setTotalVisits(rs.getLong("USER_COUNT"));
				return analitics;
			}
		
		});
		
		return analiticsData;
	
	}
}
