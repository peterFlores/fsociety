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

import com.redsocial.web.app.Models.Visit;
import com.redsocial.web.app.Services.IVisitiService;

@Service
public class visitDAO implements IVisitiService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Transactional
	@Override
	public void createVisit(Visit visit) {
		// TODO Auto-generated method stub
	
	jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("REGISTER_VISIT");
	Map<String, Object> Map = new HashMap<String, Object>();
	Map.put("PUSER_ID", visit.getIdUser());
	Map.put("PUSER_ID_VISITOR", visit.getIdVisitor());

	SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

	jdbcCall.execute(src);
	
		
	}

}
