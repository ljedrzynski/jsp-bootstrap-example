package pl.edu.pja.tin.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import pl.edu.pja.tin.model.Car;
import pl.edu.pja.tin.model.CarRental;
import pl.edu.pja.tin.model.Person;
import pl.edu.pja.tin.service.CarRentalService;
import pl.edu.pja.tin.service.CarService;
import pl.edu.pja.tin.service.PersonService;

@Model
@SessionScoped
public class CarRentalController implements Serializable {
	private CarRental formCarRental;
	private List<CarRental> carRentalList;
	private Long carId;
	private Long personId;
	private List<Car> availCarList;

	@Inject
	private CarRentalService pService;

	@Inject
	private CarService carService;

	@Inject
	private PersonService personService;

	public void initNewCarRental() {
		this.formCarRental = new CarRental();
	}

	public String showNewCarRentalForm() {
		resetControllerState();
		initNewCarRental();
		return "carRentalForm";
	}

	public String showEditCarRentalForm(CarRental p) {
		this.formCarRental = pService.getCarRentalDetails(p.getId());
		return "carRentalForm";
	}

	public String showCarRentalDetails(CarRental p) {
		this.formCarRental = pService.getCarRentalDetails(p.getId());
		return "carRentalDetails";
	}

	public String showCarRentalList() {
		// this.carRentalList = pService.getAllCarRentals();
		return "carRentalList";
	}

	public String saveNewCarRental() {
		if (personId != null && carId != null && formCarRental.getStartDate() != null
				&& formCarRental.getEndDate() != null) {
			formCarRental.setCar(carService.getCarDetails(carId));
			formCarRental.setPerson(personService.getPersonDetails(personId));
			pService.persistCarRental(formCarRental);
			resetControllerState();
			return "carRentalList";
		}

		return "carRentalForm";
	}

	public String updateCarRental() {
		pService.updateCarRental(formCarRental);
		resetControllerState();
		return "carRentalList";
	}

	public void resetControllerState() {
		this.formCarRental = null;
		this.carRentalList = null;
		this.personId = null;
		this.carId = null;
		this.availCarList = null;
	}

	public List<CarRental> getCarRentalList() {
		if (this.carRentalList == null) {
			this.carRentalList = pService.getAllCarRentals();
		}
		return carRentalList;
	}

	public void fillCarList() {
		Date startDate = this.formCarRental.getStartDate();
		Date endDate = this.formCarRental.getEndDate();
		if (startDate == null || endDate == null || startDate.before(new Date()) || startDate.after(endDate)) {
			return;
		}
		availCarList = carService.getAvailableCars(startDate, endDate);
	}

	public CarRental getFormCarRental() {
		return formCarRental;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public List<Car> getAvailCarList() {
		return availCarList;
	}

	public void setAvailCarList(List<Car> carList) {
		this.availCarList = carList;
	}

}