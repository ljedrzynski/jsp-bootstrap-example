package pl.edu.pja.tin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.edu.pja.tin.model.CarRental;

public class CarRentalDao {

	@PersistenceContext
	private EntityManager em;

	public void persistCarRental(CarRental carRental) {
		em.persist(carRental);
	}

	public void updateCarRental(CarRental carRental) {
		em.merge(carRental);
	}

	public List<CarRental> getAllCarRentals() {
		TypedQuery<CarRental> q = em.createQuery("from CarRental", CarRental.class);
		List<CarRental> resultList = q.getResultList();
		return resultList;
	}

	public CarRental getCarRentalDetails(Long carRentalId) {
		TypedQuery<CarRental> q = em.createQuery("from CarRental where id = :id", CarRental.class);
		q.setParameter("id", carRentalId);
		CarRental res = q.getSingleResult();
		return res;
	}

}
