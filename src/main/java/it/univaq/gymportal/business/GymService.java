package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Gym;

import java.util.List;

public interface GymService {
	
	List<Gym> getAllGyms() throws ServiceException;
	List<Gym> getGymsByRegion(String region) throws ServiceException;
	List<Gym> getGymsByName(String hint) throws ServiceException;
	Gym getGym(long id) throws ServiceException;
	Gym getGymWithManager(long id) throws ServiceException;
	long createGym(Gym gym) throws ServiceException;
	void updateGym(Gym gym) throws ServiceException;
	void deleteGym(long id) throws ServiceException;

}
