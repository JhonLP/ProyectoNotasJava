package mx.itson.nota.negocio;


import java.util.List;

import mx.itson.nota.entidades.Nota;
import mx.itson.nota.persistencia.NotaPersistencia;



public class NotaNegocio {

	public Nota guardar(String titulo, String descripcion){
		Nota nota = new Nota(titulo, descripcion);
		NotaPersistencia notaPersistencia = new NotaPersistencia();
		return notaPersistencia.guardar(nota);
}
	public Nota consultar(int id) {
		NotaPersistencia notaPersistencia = new NotaPersistencia();
		return notaPersistencia.consultar(id);
	}
	public Nota editar(Nota nota) {
		NotaPersistencia notaPersistencia = new NotaPersistencia();
		return notaPersistencia.editar(nota);
	}
	public boolean eliminar(Nota nota) {
		NotaPersistencia notaPersistencia = new NotaPersistencia();
		return notaPersistencia.eliminar(nota);
	}
	public List<Nota> consultarTodos() {
		NotaPersistencia notaPersistencia = new NotaPersistencia();
		return notaPersistencia.consultarTodos();
	}
}