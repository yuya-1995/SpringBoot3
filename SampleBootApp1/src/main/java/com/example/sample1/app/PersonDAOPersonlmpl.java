package com.example.sample1.app;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOPersonlmpl implements PersonDAO<Person>{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PersonDAOPersonlmpl() {
		super();
	}
	
	@Override
	public List<Person> getAll(){
		Query query = entityManager.createQuery("from Person");
		@SuppressWarnings("unchecked")
		List<Person> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
	@Override
	public Person findById(long id) {
		return (Person)entityManager.createQuery("from Person where id =" + id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findByName(String name){
		return (List<Person>)entityManager.createQuery("from Person where name ='" + name + "'").getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> find(String fstr){
		List<Person> list = null;
		Query query = entityManager.createNamedQuery("findWithName")
				.setParameter("fname", "%" + fstr + "%");
//		String qstr = "from Person where id = ?1 or name like ?2 or mail like ?3";
//		Long fid = 0L;
//		try {
//			fid = Long.parseLong(fstr);
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//		Query query = entityManager.createQuery(qstr)
//				.setParameter(1, fid)
//				.setParameter(2, "%" + fstr + "%")
//				.setParameter(3, fstr + "%@%");
		list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findByAge(int min, int max){
		return (List<Person>)entityManager
				.createNamedQuery("findByaAge")
				.setParameter("min", min)
				.setParameter("max", max)
				.getResultList();
	}

}
