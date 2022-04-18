package ar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estudiante {
	private String nombre;
	private String apellido;
	private int edad;
	private int numeroLegajo;
	private String carrera;
	private List<Materia> materias;

	public Estudiante(String nombre, String apellido, int edad, int numeroLegajo, String carrera) {
		this.nombre = new NotNullNotEmpty(nombre).value();
		this.apellido = new NotNullNotEmpty(apellido).value();
		this.edad = new NotNullNotEmpty(edad).valueNum();
		this.numeroLegajo = new NotNullNotEmpty(numeroLegajo).valueNum();
		this.carrera = new NotNullNotEmpty(carrera).value();
		this.materias = new ArrayList<>();
	}

	public Estudiante(String nombre, String apellido, int edad, int numeroLegajo, String carrera,
			List<Materia> materias) {
		this(nombre, apellido, edad, numeroLegajo, carrera);
		this.materias = materias;
	}

	public void addMateria(String materia) {
		this.materias.add(new Materia(new NotNullNotEmpty(materia).value()));
	}

	public void addMaterias(String[] nombres) {

		String[] materias = new NotNullNotEmpty(nombres).values();

		var mat = List.of(materias).stream().map((n) -> {
			return new Materia(n);
		}).collect(Collectors.toList());

		this.materias.addAll(mat);
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", numeroLegajo="
				+ numeroLegajo + ", carrera=" + carrera + ", materias=" + materias + "]";
	}

	public Map<String, Object> toMap() {
		var map = new HashMap<String, Object>(Map.of("nombre", nombre, "apellido", apellido, "edad", edad,
				"numeroLegajo", numeroLegajo, "carrera", carrera));

		if (this.materias != null && this.materias.size() > 0) {
			map.put("materias", materias.stream().map((e) -> e.toMap()).collect(Collectors.toList()));
			// map.put("materias", materias.stream().map((e) -> Map.of("materia",
			// e)).collect(Collectors.toList()));
			// map.put("telefonos", telefonos.stream().map((e) ->
			// e.toMap()).collect(Collectors.toList()));
		}
		return map;
	}

	public boolean containsApellido(String apellido) {
		return this.apellido.contains(apellido);
	}

	public boolean containsNumeroDeLegajo(int numeroLegajo) {
		return this.numeroLegajo == numeroLegajo;
	}

}
