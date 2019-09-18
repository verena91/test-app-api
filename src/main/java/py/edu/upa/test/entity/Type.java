package py.edu.upa.test.entity;


import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="type", schema="public")

public class Type implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "type_id_type_seq", sequenceName = "type_id_type_seq", allocationSize = 1, schema= "public")
    @GeneratedValue(generator = "type_id_type_seq")
	
	private Integer id;
	private String nombre;
	private String description;
	private boolean deleted;
	
	public Type() { 
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
