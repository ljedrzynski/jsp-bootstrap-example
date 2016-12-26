package pl.edu.pja.tin.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pl.edu.pja.tin.dao.PersonDao;

@Stateless
public class PersonService implements Serializable {
	
	@Inject 
	private PersonDao personDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistPerson(pl.edu.pja.tin.model.Person person) {
		personDAO.persistPerson(person);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePerson(pl.edu.pja.tin.model.Person person) {
		personDAO.updatePerson(person);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<pl.edu.pja.tin.model.Person> getAllPersons() {
		return personDAO.getAllPersons();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public pl.edu.pja.tin.model.Person getPersonDetails(Long personId) {
		return personDAO.getPersonDetails(personId);
	}
	
}
