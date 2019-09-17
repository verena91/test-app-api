package py.edu.upa.test.entity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="type", schema="public")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "type_id_seq", sequenceName = "type_id_seq", allocationSize = 1, schema= "public")
    @GeneratedValue(generator = "type_id_seq")
	private Integer id;
	private String descripcion;
	private String nombre;

	public Type() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setName(String nombre) {
		this.nombre = nombre;
	}
	


}