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

import com.redsocial.web.app.Models.PageVisitor;
import com.redsocial.web.app.Services.IPageVisitorService;

@Service
public class PageVisitorDAO implements IPageVisitorService {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Transactional
	@Override
	public void createVisit(PageVisitor visitor) throws Exception {
		// TODO Auto-generated method stub
		
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("create_visitpage");
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PIP_VISITOR", visitor.getIpVisitor());
		Map.put("PPAGE_DESCRIPTION", visitor.getPageDescription());
		
		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		jdbcCall.execute(src);
		
	}

}
