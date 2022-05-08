package ar.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.model.Estudiante;
import ar.model.Materia;
import ar.model.PersonaException;
import ar.servicios.Estudiantes;
import ar.servicios.Materias;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPIEstudiante {

	private Estudiantes estudiantes;
	private Materias materias;
	private int webPort;

	public WebAPIEstudiante(Estudiantes estudiantes, Materias materias, int webPort) {
		this.estudiantes = estudiantes;
		this.materias = materias;
		this.webPort = webPort;
	}

	public void start() {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(this.webPort);
		app.get("/estudiantes", traerEstudiantes());
		app.get("/estudiantesId", traerEstudiantesPorId());
		app.post("/estudiantes", crearEstudiante());
		app.get("/materias", traerMaterias());

		app.exception(PersonaException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
			// log error in a stream...
		});

		app.exception(Exception.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió.: " + e.getMessage()));
			// log error in a stream...
		});
	}

	private Handler crearEstudiante() {
		return ctx -> {
			EstudianteDto dto = ctx.bodyAsClass(EstudianteDto.class);
			this.estudiantes.crearEstudiante(dto.getNombre(), dto.getApellido(), dto.getEdad(), dto.getNumeroLegajo(),
					dto.getCarrera(), dto.getMaterias());
			ctx.json(Map.of("result", "success"));
		};
	}

	private Handler traerEstudiantes() {
		return ctx -> {
			String apellido = ctx.queryParam("apellido");
			List<Estudiante> estudiantes = this.estudiantes.estudiantes(apellido);

			var list = new ArrayList<Map<String, Object>>();

			for (Estudiante e : estudiantes) {
				list.add(e.toMap());
			}

			ctx.json(Map.of("result", "success", "estudiantes", list));

		};
	}

	private Handler traerEstudiantesPorIdViejo() {
		return ctx -> {
			String numeroLegajo = ctx.queryParam("numeroLegajo");
			List<Estudiante> estudiantes = this.estudiantes.estudiantes(Integer.parseInt(numeroLegajo));

			var list = new ArrayList<Map<String, Object>>();

			for (Estudiante e : estudiantes) {
				list.add(e.toMap());
			}

			ctx.json(Map.of("result", "success", "estudiantes", list));

		};
	}

	private Handler traerMaterias() {
		return ctx -> {
			List<Materia> materias = this.materias.materias();
			var list = new ArrayList<Map<String, String>>();

			for (Materia m : materias) {
				list.add(m.toMap());
			}

			ctx.json(Map.of("result", "success", "materias", list));

		};
	}

	private Handler traerEstudiantesPorId() {
		return ctx -> {

			String nroLegajo = ctx.queryParam("numeroLegajo");

			List<Estudiante> estudiantes = new ArrayList<Estudiante>();

			if (nroLegajo != null) {
				if (!nroLegajo.isBlank() || !nroLegajo.isEmpty()) {
					estudiantes = this.estudiantes.estudiantes(Integer.parseInt(nroLegajo));
				} else {
					estudiantes = this.estudiantes.estudiantes(0);
				}
			} else {
				estudiantes = this.estudiantes.estudiantes(0);
			}

			var list = new ArrayList<Map<String, Object>>();

			for (Estudiante e : estudiantes) {
				list.add(e.toMap());
			}

			ctx.json(Map.of("result", "success", "estudiantes", list));

		};
	}
}