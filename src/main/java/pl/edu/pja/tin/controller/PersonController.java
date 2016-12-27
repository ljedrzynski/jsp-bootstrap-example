package pl.edu.pja.tin.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import pl.edu.pja.tin.model.Person;
import pl.edu.pja.tin.service.PersonService;

@Model
@SessionScoped
public class PersonController implements Serializable {
	private Person formPerson;
	private List<Person> personList;

	@Inject
	private PersonService pService;

	public void initNewPerson() {
		this.formPerson = new Person();
	}

	public String showNewPersonForm() {
		initNewPerson();
		return "personForm";
	}

	public String showEditPersonForm(Person p) {
		this.formPerson = pService.getPersonDetails(p.getId());
		return "personForm";
	}

	public String showPersonDetails(Person p) {
		this.formPerson = pService.getPersonDetails(p.getId());
		return "personDetails";
	}

	public String showPersonList() {
		// this.personList = pService.getAllPersons();
		return "personList";
	}

	public String saveNewPerson() {
		pService.persistPerson(formPerson);
		resetControllerState();
		return "personList";
	}

	public String updatePerson() {
		pService.updatePerson(formPerson);
		resetControllerState();
		return "personList";
	}

	public void resetControllerState() {
		this.formPerson = null;
		this.personList = null;
	}

	public List<Person> getPersonList() {
		if (this.personList == null || this.personList.size() == 0) {
			this.personList = pService.getAllPersons();
		}
		return personList;
	}

	public Person getFormPerson() {
		return formPerson;
	}

}
