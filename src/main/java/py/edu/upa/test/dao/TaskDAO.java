package py.edu.upa.test.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import py.edu.upa.test.entity.Task;

@Stateless
public class TaskDAO {
	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Task> findWithFilter(String filter) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);

		criteria.add(Restrictions.and(Restrictions.ilike("description", filter),
				Restrictions.or(Restrictions.eq("deleted", false), Restrictions.isNull("deleted"))));

		return criteria.list();

	}

	
	
	@SuppressWarnings("unchecked")
	public List<Task> find() {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);

		criteria.add(Restrictions.or(Restrictions.eq("deleted", false), Restrictions.isNull("deleted")));

		return criteria.list();

	}

	public Task findById(Integer id) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);

		criteria.add(Restrictions.eq("id", id));

		return (Task) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Task> findPaginated(Integer page, Integer quantity) {

		Integer inicio; 
		inicio = (page - 1) * quantity; //cual va a ser el primer registro
		
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);

		criteria.setFirstResult(inicio); // el primer resultado que se quiere visualizar
		criteria.setMaxResults(quantity); // la cantidad maxima de resultados que se quiere ver

		return (List<Task>) criteria.list(); // al poner entre parentesis, convertis a ese dato
											 //	en el tipo de lista de task

	
	}
	
	public void insert(Task t) {
		entityManager.persist(t);
	}

	public void update(Integer id, Task task) {
		Task t = findById(id);
		t.setCreationDate(task.getCreationDate());
		t.setDescription(task.getDescription());
		t.setDeleted(task.getDeleted());
		t.setFile(task.getFile());
		t.setLimitDate(task.getLimitDate());
		t.setName(task.getName());
		t.setUpdateDate(task.getUpdateDate());
		entityManager.merge(t);
	}

	public void delete(Integer id) {
		Task t = findById(id);
		t.setDeleted(true);
		entityManager.merge(t);
	}

	@SuppressWarnings("unchecked")
	public List<Task> findByType(Integer id_type) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);
		criteria.createAlias("type", "typexd");
		criteria.add(Restrictions.eq("typexd.id", id_type));
		// criteria.add(Restrictions.eq("id", id_type));

		return (List<Task>) criteria.list();

	}

	
	}
