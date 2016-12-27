package pl.edu.pja.tin.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import pl.edu.pja.tin.model.Car;
import pl.edu.pja.tin.service.CarService;

@Model
@SessionScoped
public class CarController implements Serializable {
	private Car formCar;
	private List<Car> carList;

	@Inject
	private CarService pService;

	public void initNewCar() {
		this.formCar = new Car();
	}

	public String showNewCarForm() {
		initNewCar();
		return "carForm";
	}

	public String showEditCarForm(Car p) {
		this.formCar = pService.getCarDetails(p.getId());
		return "carForm";
	}

	public String showCarDetails(Car p) {
		this.formCar = pService.getCarDetails(p.getId());
		return "carDetails";
	}

	public String showCarList() {
//		this.carList = pService.getAllCars();
		return "carList";
	}

	public String saveNewCar() {
		pService.persistCar(formCar);
		resetControllerState();
		return "carList";
	}

	public String updateCar() {
		pService.updateCar(formCar);
		resetControllerState();
		return "carList";
	}
	
	public void resetControllerState() {
		this.formCar = null;
		this.carList = null;
	}
	
	public List<Car> getCarList() {
		if(this.carList == null || this.carList.size() == 0) {
			this.carList = pService.getAllCars();
		}
		return carList;
	}
	public Car getFormCar() {
		return formCar;
	}
}