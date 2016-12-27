package pl.edu.pja.tin.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pl.edu.pja.tin.dao.CarRentalDao;

@Stateless
public class CarRentalService implements Serializable {

	@Inject
	private CarRentalDao carRentalDao;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistCarRental(pl.edu.pja.tin.model.CarRental carRental) {
		carRentalDao.persistCarRental(carRental);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateCarRental(pl.edu.pja.tin.model.CarRental carRental) {
		carRentalDao.updateCarRental(carRental);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<pl.edu.pja.tin.model.CarRental> getAllCarRentals() {
		return carRentalDao.getAllCarRentals();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public pl.edu.pja.tin.model.CarRental getCarRentalDetails(Long carRentalId) {
		return carRentalDao.getCarRentalDetails(carRentalId);
	}

}
