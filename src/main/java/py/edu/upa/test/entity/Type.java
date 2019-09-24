package py.edu.upa.test.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "type", schema = "public")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "type_id_seq", sequenceName = "type_id_seq", allocationSize = 1, schema = "public")
	@GeneratedValue(generator = "type_id_seq")
	private Integer id;
	private String descripcion;
	private String nombre;
	private Boolean delete;

	public Boolean getDeleted() {
		return delete;
	}

	public void setDeleted(Boolean delete) {
		this.delete = delete;
	}

	public Type () {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
