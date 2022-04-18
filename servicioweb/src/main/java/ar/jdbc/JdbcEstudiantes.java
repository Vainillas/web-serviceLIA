package ar.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import ar.model.Estudiante;
import ar.servicios.Estudiantes;

public class JdbcEstudiantes implements Estudiantes {

	@Override
	public List<Estudiante> estudiantes(String apellido) {
		Estudiante e1 = new Estudiante("Joaquin", "Garcia", 27, 213788, "Lic. Sistemas");
		Estudiante e2 = new Estudiante("Emilio", "Perez", 21, 574841, "Lic. Agrónoma");
		Estudiante e3 = new Estudiante("Ernesto", "Perez", 42, 321654, "Abogacía");

		e1.addMateria("Orientación a Objetos 1");
		e1.addMateria("Seminario de Lenguajes");
		e2.addMateria("Química Orgánica");
		e3.addMateria("Historia del Derecho Romano");

		var estudiantes = List.of(e1, e2, e3);

		if (apellido == null || apellido.isEmpty())
			return estudiantes;

		return estudiantes.stream().filter((p) -> {
			return p.containsApellido(apellido);
		}).collect(Collectors.toList());
	}

	@Override
	public List<Estudiante> estudiantes(int numeroLegajo) {
		Estudiante e1 = new Estudiante("Joaquin", "Garcia", 27, 213788, "Lic. Sistemas");
		Estudiante e2 = new Estudiante("Emilio", "Perez", 21, 574841, "Lic. Agrónoma");
		Estudiante e3 = new Estudiante("Ernesto", "Perez", 42, 321654, "Abogacía");

		e1.addMateria("Orientación a Objetos 1");
		e1.addMateria("Seminario de Lenguajes");
		e2.addMateria("Química Orgánica");
		e3.addMateria("Historia del Derecho Romano");

		var estudiantes = List.of(e1, e2, e3);

		if (numeroLegajo == 0)
			return estudiantes;

		return estudiantes.stream().filter((p) -> {
			return p.containsNumeroDeLegajo(numeroLegajo);
		}).collect(Collectors.toList());
	}

	@Override
	public void crearEstudiante(String nombre, String apellido, int edad, int numeroLegajo, String carrera,
			String[] materias) {
		Estudiante e = new Estudiante(nombre, apellido, edad, numeroLegajo, carrera);
		e.addMaterias(materias);

		System.out.println(e.toString());

	}

}
