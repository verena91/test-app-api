package py.edu.upa.test.business;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import py.edu.upa.test.dao.TaskDAO;
import py.edu.upa.test.entity.Task;

@Named
@RequestScoped
public class TaskBC {
	
    @EJB
	private TaskDAO dao;
	

	public List<Task> find() {
		return dao.find();
	}
	
	public Task findById(Integer id) {
		return dao.findById(id);
	}
	
	public void insert(Task t){
		dao.insert(t);
	}
	
	public void update(Integer id, Task t){
		dao.update(id, t);
	}
	
	public void delete(Integer id){
		dao.delete(id);
	}
	
	public List<Task> getWithFilter(String findByType) {
		return dao.findWithFilter(findByType);
	}
	public List<Task> getfindByType(int type) {
		return dao.findByType(type);
}
}

