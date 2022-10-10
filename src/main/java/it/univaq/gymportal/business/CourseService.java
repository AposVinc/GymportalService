package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Course;

import java.util.List;

public interface CourseService {
	
	List<Course> getCoursesByGym(long gymId) throws ServiceException;
	List<Course> getCoursesByName(long gymId, String hint) throws ServiceException;
	Course getCourse(long id) throws ServiceException;
	long createCourse(Course course) throws ServiceException;
	void updateCourse(Course course) throws ServiceException;
	void deleteCourse(long id) throws ServiceException;

}
