package ar.model;

import java.util.Map;

public class Materia {
	private String nombre;

	public Materia(String nombre) {
		this.nombre = new NotNullNotEmpty(nombre).value();
	}

	public String nombre() {
		return nombre;
	}

	public Map<String, String> toMap() {
		return Map.of("materia", nombre);
	}

	@Override
	public String toString() {
		return "Materia [nombre=" + nombre + "]";
	}

	public boolean containsNombre(String nombre) {
		return this.nombre.contains(nombre);
	}

}
