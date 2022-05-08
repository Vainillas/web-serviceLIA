package ar.servicios;

import java.util.List;

import ar.model.Estudiante;

public interface Estudiantes {
	List<Estudiante> estudiantes(String apellido);

	List<Estudiante> estudiantes(Integer numeroLegajo);

	void crearEstudiante(String nombre, String apellido, int edad, int numeroLegajo, String carrera, String[] materias);

}
