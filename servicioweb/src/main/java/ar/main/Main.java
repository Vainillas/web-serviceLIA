package ar.main;

import ar.jdbc.JdbcEstudiantes;
import ar.jdbc.JdbcMaterias;
import ar.web.WebAPIEstudiante;

public class Main {

	public static void main(String[] args) {
		WebAPIEstudiante servicio = new WebAPIEstudiante(new JdbcEstudiantes(), new JdbcMaterias(), 1234);
		servicio.start();
	}
}
