package ar.jdbc;

import java.util.List;
import java.util.stream.Collectors;

import ar.model.Persona;
import ar.servicios.Personas;

public class JdbcPersonas implements Personas {

 //constructor con dependencias
 
 @Override
 public List<Persona> personas(String apellido) {
  Persona p1 = new Persona("Joaquin", "Garcia");
  Persona p2 = new Persona("Emilio", "Peroz");
  Persona p3 = new Persona("Ernesto", "Perez");
  
  p1.addDireccion("San Martin 123");
  p2.addDireccion("Belgrano 34");
  p3.addDireccion("Bs As 908");
  
  p1.addTelefono("12345678");
  p1.addTelefono("45335678");
  p2.addTelefono("45633334");
  p3.addTelefono("11222356");
  
  var personas = List.of(p1, p2, p3);
  
  if (apellido == null || apellido.isEmpty())
   return personas;
  
  return personas.stream().filter((p) -> {
   return p.containsApellido(apellido);
  }).collect(Collectors.toList());
 }

 @Override
 public void crearPersona(String nombre, String apellido,
   String direccion, String[] telefonos, String localidadId) {

  Persona p = new Persona(nombre, apellido);
  p.addDireccion(direccion);
  p.addTelefonos(telefonos);
  
  System.out.println(p.toString());
  System.out.println(localidadId);
 }
}
