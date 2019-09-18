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

import py.edu.upa.test.entity.Type;

@Stateless
public class TypeDAO {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Type> findWithFilter(String filter) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);
		
		criteria.add(Restrictions.and(
				Restrictions.ilike("name", filter),
				Restrictions.or(
						Restrictions.eq("deleted", false),
						Restrictions.isNull("deleted")
				)));
		
		return criteria.list();

	}

	
	@SuppressWarnings("unchecked")
	public List<Type> find() {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);
		
		criteria.add(Restrictions.or(
				Restrictions.eq("deleted", false),
				Restrictions.isNull("deleted")));
		
		return criteria.list();

	}
	
	public Type findById(Integer id) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);
		
		criteria.add(Restrictions.eq("id", id));
		
		return (Type) criteria.uniqueResult();

	}
	
	public void insert(Type t){
		entityManager.persist(t);
	}
	
	public void update(Integer id, Type type){
		Type t = findById(id);
		t.setDescription(type.getDescription());
		t.setDeleted(type.getDeleted());
		t.setName(type.getName());
		entityManager.merge(t);
	}
	
	public void delete(Integer id){
		Type t = findById(id);
		t.setDeleted(true);
		entityManager.merge(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<Type> getPaginated(int pageSize, int first,
			String sortField, String sortOrder) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);
		// add order by
		if(sortField != null) {	
			Order order = Order.asc(sortField);
			if (sortOrder.equals("DESC")){
				order = Order.desc(sortField);
			} else {
				order = Order.desc(sortField);
			}
			criteria.addOrder(order);
		} else { // orden por defecto
			Order order = Order.asc("id");
			criteria.addOrder(order);
		}

		// add limit, offset
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria.list();

	}
}