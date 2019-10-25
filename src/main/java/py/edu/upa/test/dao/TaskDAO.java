package py.edu.upa.test.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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
		
		criteria.add(Restrictions.and(
				Restrictions.ilike("description", filter),
				Restrictions.or(
						Restrictions.eq("deleted", false),
						Restrictions.isNull("deleted")
				)));
		
		return criteria.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getFilteredByType(int filter) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);
		criteria.createAlias("type", "pepe");
		criteria.add(Restrictions.and(
				Restrictions.eq("pepe.id", filter),
				Restrictions.or(
						Restrictions.eq("deleted", false),
						Restrictions.isNull("deleted")
				)));
		
		return criteria.list();

	}

	
	@SuppressWarnings("unchecked")
	public List<Task> find() {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);
		
		criteria.add(Restrictions.or(
				Restrictions.eq("deleted", false),
				Restrictions.isNull("deleted")));
		
		return criteria.list();

	}
	
	public Task findById(Integer id) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);
		
		criteria.add(Restrictions.eq("id", id));
		
		return (Task) criteria.uniqueResult();

	}
	
	public void insert(Task t){
		entityManager.persist(t);
	}
	
	public void update(Integer id, Task task){
		Task t = findById(id);
		t.setCreationDate(task.getCreationDate());
		t.setDescription(task.getDescription());
		t.setDeleted(task.getDeleted());
		t.setFile(task.getFile());
		t.setLimitDate(task.getLimitDate());
		t.setName(task.getName());
		t.setUpdateDate(task.getUpdateDate());
		t.setResuelta(task.getResuelta());
		t.setType(task.getType());
		entityManager.merge(t);
	}
	
	public void delete(Integer id){
		Task t = findById(id);
		t.setDeleted(true);
		entityManager.merge(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getPaginated(int pageSize, int page,
			String sortField, String sortOrder) { // sortOrder = ASC, DESC
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Task.class);
		criteria.add(Restrictions.or(
				Restrictions.eq("deleted", false),
				Restrictions.isNull("deleted")));
		// add order by
		if(sortField != null) {	
			Order order = Order.asc(sortField);
			if (sortOrder.equals("DESC")){
				order = Order.desc(sortField);
			}
			criteria.addOrder(order);
		} else { // orden por defecto
			Order order = Order.asc("id");
			criteria.addOrder(order);
		}

		// add limit, offset
		criteria.setFirstResult(page * pageSize);
		criteria.setMaxResults(pageSize);
		return criteria.list();

	}
}