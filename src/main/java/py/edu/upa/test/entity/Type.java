package py.edu.upa.test.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="type", schema="public") 
public class Type implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "type_id_seq", sequenceName = "type_id_seq", allocationSize = 1, schema= "public")
    @GeneratedValue(generator = "type_id_seq")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="delete")
	private boolean delete;
	
	public Type() {		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}	
	
}
