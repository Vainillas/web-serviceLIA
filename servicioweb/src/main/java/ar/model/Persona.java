package ar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Persona {

 private String nombre;
 private String apellido;
 private List<Telefono> telefonos;
 private Direccion direccion;

 public Persona(String nombre, String apellido) {
  this.nombre = new NotNullNotEmpty(nombre).value();
  this.apellido = new NotNullNotEmpty(apellido).value();
  this.telefonos = new ArrayList<>();
 }

 public void addDireccion(String direccion) {
  this.direccion = new Direccion(new NotNullNotEmpty(direccion).value());
 }

 public void addTelefono(String numero) {
  this.telefonos.add(new Telefono(new NotNullNotEmpty(numero).value()));
 }

 public void addTelefonos(String[] numeros) {
  
  String[] telefonos = new NotNullNotEmpty(numeros).values();
  
  var tel = List.of(telefonos).stream().map((n) -> {
   return new Telefono(n);
  }).collect(Collectors.toList());
  
  this.telefonos.addAll(tel);
 }
 
 public String nombre() {
  return nombre + " " + apellido;
 }

 @Override
 public String toString() {
  return "Persona [nombre=" + nombre + ", apellido=" + apellido
    + ", telefonos=" + telefonos + ", direccion=" + direccion + "]";
 }

 public Map<String, Object> toMap() {
  var map = new HashMap<String, Object>(
    Map.of("nombre", nombre, "apellido", apellido));

  if (this.direccion != null) {
   map.put("direccion", this.direccion.toMap());
  }

  if (this.telefonos != null && this.telefonos.size() > 0) {
   map.put("telefonos", telefonos.stream().map((e) -> e.toMap())
     .collect(Collectors.toList()));
  }
  return map;
 }

 public boolean containsApellido(String apellido) {
  return this.apellido.contains(apellido);
 }
}
