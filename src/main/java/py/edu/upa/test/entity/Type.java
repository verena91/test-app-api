package py.edu.upa.test.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type implements Serializable {
		private static final long serialVersionUID = 1L;
		
	@Id
	private Integer id_type;
	@Column(name = "nombre_type")
	private String nombre_type;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	private Boolean deleted;
	public Type() {
	}

	public Integer getId() {
		return this.id_type;
	}

	public void setId(Integer id_type) {
		this.id_type = id_type;
	}

	public String getNombre() {
		return this.nombre_type;
	}

	public void setNombre(String nombre_type) {
		this.nombre_type = nombre_type;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
