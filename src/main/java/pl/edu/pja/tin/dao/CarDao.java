package pl.edu.pja.tin.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pl.edu.pja.tin.model.Car;
import pl.edu.pja.tin.model.CarRental;

public class CarDao {

	@PersistenceContext
	private EntityManager em;

	public void persistCar(Car car) {
		em.persist(car);
	}

	public void updateCar(Car car) {
		em.merge(car);
	}

	public List<Car> getAllCars() {
		TypedQuery<Car> q = em.createQuery("from Car", Car.class);
		List<Car> resultList = q.getResultList();
		return resultList;
	}

	public Car getCarDetails(Long carId) {
		// w przypadku pobierania powiązanych obiektów wymaganych dla widoku
		// szczegółów
		// nalezy uzyc opcji 'left outer join fetch' w zapytaniu
		TypedQuery<Car> q = em.createQuery("from Car where id = :id", Car.class);
		q.setParameter("id", carId);
		Car res = q.getSingleResult();
		return res;
	}

	public List<Car> getAvailableCars(Date from, Date to) {
		TypedQuery<Car> q = em
				.createQuery("select c from Car c where not exists (select r from CarRental r where r.car = c and "
						+ " r.startDate < :endDate and r.endDate > :startDate)", Car.class);
		q.setParameter("startDate", from);
		q.setParameter("endDate", to);
		List<Car> resultList = q.getResultList();
		return resultList;
	}
}
