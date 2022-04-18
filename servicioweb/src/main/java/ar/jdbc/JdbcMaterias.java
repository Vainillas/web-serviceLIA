package ar.jdbc;

import java.util.List;

import ar.model.Materia;
import ar.servicios.Materias;

public class JdbcMaterias implements Materias {

	public List<Materia> materias() {
		Materia m1 = new Materia("Orientación a Objetos 1");
		Materia m2 = new Materia("Seminario de Lenguajes");
		Materia m3 = new Materia("Química Orgánica");
		Materia m4 = new Materia("Historia del Derecho Romano");
		var materias = List.of(m1, m2, m3, m4);

		return materias;
	}

}
