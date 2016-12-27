package pl.edu.pja.tin.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pl.edu.pja.tin.dao.CarDao;

@Stateless
public class CarService implements Serializable {

	@Inject
	private CarDao carDao;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistCar(pl.edu.pja.tin.model.Car car) {
		carDao.persistCar(car);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateCar(pl.edu.pja.tin.model.Car car) {
		carDao.updateCar(car);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<pl.edu.pja.tin.model.Car> getAllCars() {
		return carDao.getAllCars();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public pl.edu.pja.tin.model.Car getCarDetails(Long carId) {
		return carDao.getCarDetails(carId);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<pl.edu.pja.tin.model.Car> getAvailableCars(Date from, Date to) {
		return carDao.getAvailableCars(from, to);
	}

}
