package pl.edu.pja.tin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.edu.pja.tin.model.Person;

public class PersonDao {
	
	@PersistenceContext
    private EntityManager em;
    
	public void persistPerson(Person person) {
		em.persist(person);
	}
	
	public void updatePerson(Person person) {
		em.merge(person);
	}
	
	public List<Person> getAllPersons() {
		TypedQuery<Person> q = em.createQuery("from Person", Person.class);
		List<Person> resultList = q.getResultList();
		return resultList;
	}
	
	public Person getPersonDetails(Long personId) {
		//w przypadku pobierania powiązanych obiektów wymaganych dla widoku szczegółów
		//nalezy uzyc opcji 'left outer join fetch' w zapytaniu
		TypedQuery<Person> q = em.createQuery("from Person where id = :id", Person.class);
		q.setParameter("id", personId);
		Person res = q.getSingleResult();
		return res;
	}
}
