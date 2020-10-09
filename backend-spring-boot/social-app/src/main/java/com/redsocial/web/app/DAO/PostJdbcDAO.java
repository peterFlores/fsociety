package com.redsocial.web.app.DAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.redsocial.web.app.Models.PostJDBC;
import com.redsocial.web.app.Services.PostService;

@Service
public class PostJdbcDAO implements PostService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;

	@Override
	public void createPost(PostJDBC post) {
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("CREATE_POST");
		
		Map<String, Object> Map = new HashMap<String, Object>();

		Map.put("PPOST_CONTENT", post.getPostContent());
		Map.put("PPOST_IMAGE_PATH", post.getImagePath());
		Map.put("PUSER_ID", post.getUserId());
		
		SqlParameterSource src = new MapSqlParameterSource().addValues(Map);

		jdbcCall.execute(src);

	}

	@Override
	public void deletePost(long idPost) {
		
		jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("DELETE_POST");
		SqlParameterSource src = new MapSqlParameterSource().addValue("PPOST_ID", idPost);
		jdbcCall.execute(src);

	}

}
