package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Course;
import it.univaq.gymportal.model.FavoriteCourse;

import java.util.List;

public interface FavoriteCourseService {

    long createFavoriteCourse (FavoriteCourse favoriteCourse) throws ServiceException;
    List<Course> getAllFavoriteCourse(long idUser) throws ServiceException;
    void deleteFavoriteCourse(long idUser, long idCourse) throws ServiceException;

}
