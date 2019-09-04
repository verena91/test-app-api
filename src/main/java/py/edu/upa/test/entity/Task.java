package py.edu.upa.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="task", schema="public")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", allocationSize = 1, schema= "public")
    @GeneratedValue(generator = "task_id_seq")
	private Integer id;

	@Column(name="creation_date")
	private Timestamp creationDate;

	private Boolean deleted;

	private String description;

	private byte[] file;

	@Column(name="limit_date")
	private Timestamp limitDate;

	private String name;

	@Column(name="update_date")
	private Timestamp updateDate;

	public Task() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Timestamp getLimitDate() {
		return this.limitDate;
	}

	public void setLimitDate(Timestamp limitDate) {
		this.limitDate = limitDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}