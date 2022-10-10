package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FavoriteGym;
import it.univaq.gymportal.model.Gym;

import java.util.List;

public interface FavoriteGymService {

    long createFavoriteGym (FavoriteGym favoriteGym) throws ServiceException;
    List<Gym> getAllFavoriteGym(long idUser) throws ServiceException;
    void deleteFavoriteGym(long idUser, long idGym) throws ServiceException;

}
