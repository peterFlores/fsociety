package com.redsocial.web.app.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redsocial.web.app.Models.Estudiante;
import com.redsocial.web.app.Services.IEstudianteService;

@Service
public class EstudianteDAO implements IEstudianteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	@Override
	public void createEstudiante(Estudiante estudiante) {
		// TODO Auto-generated method stub


	}

	@Override
	public void updateEstudiante(Estudiante estudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEstudiante(Integer id) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = true)
	@Override
	public List<Estudiante> listEstudiantes() {

			String sql = "SELECT * FROM ESTUDIANTES";

			List<Estudiante> List = jdbcTemplate.query(sql,
					(rs, rowNum) -> new Estudiante (
							rs.getLong("ESTUDIANTE_ID"),
							rs.getString("ESTUDIANTE_NAME"),
							rs.getString("ESTUDIANTE_CARNET")
							)
					);

			return List;
	}
	


}
