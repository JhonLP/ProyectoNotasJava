package mx.itson.nota.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name="nota")
public class Nota {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	public Nota() {
	   }

	   public Nota(String titulo, String descripcion) {
	      this.titulo = titulo;
	      this.descripcion = descripcion;
	      
	   }
	   public Integer getId() {
	       return this.id;
	   }
	   
	   public void setId(Integer id) {
	       this.id = id;
	   }
	   public String getTitulo() {
	       return this.titulo;
	   }
	   
	   public void setTitulo(String titulo) {
	       this.titulo = titulo;
	   }
	   public String getDescripcion() {
	       return this.descripcion;
	   }
	   
	   public void setDescripcion(String descripcion) {
	       this.descripcion = descripcion;
	   }
}
