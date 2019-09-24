package py.edu.upa.test.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import py.edu.upa.test.entity.Type;

@Stateless
public class Type_DAO {
	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Type> findWithFilter(String filter) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);

		criteria.add(Restrictions.and(Restrictions.ilike("description", filter),
				Restrictions.or(Restrictions.eq("delete", "delete"), Restrictions.isNull("delete"))));

		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Type> find() {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);

		criteria.add(Restrictions.or(Restrictions.eq("delete", false), Restrictions.isNull("delete")));

		return criteria.list();

	}

	public Type findById(Integer id) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Type.class);

		criteria.add(Restrictions.eq("id", id));

		return (Type) criteria.uniqueResult();

	}

	public void insert(Type t) {
		entityManager.persist(t);
	}


	public void delete(Integer id){
		Type t = findById(id);
		t.setDeleted(true);
		entityManager.merge(t);
	}
	
	public void update(Integer id, Type type){
		Type t = findById(id);
		
		t.setDescripcion(type.getDescripcion());
		t.setDeleted(type.getDeleted());
		t.setNombre(type.getNombre());
		entityManager.merge(t);
	}
	
	
}