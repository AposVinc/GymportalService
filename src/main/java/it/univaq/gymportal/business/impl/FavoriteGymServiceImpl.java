package it.univaq.gymportal.business.impl;

import it.univaq.gymportal.business.FavoriteGymService;
import it.univaq.gymportal.business.Service;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FavoriteGym;
import it.univaq.gymportal.model.Gym;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteGymServiceImpl extends Service implements FavoriteGymService {

    private static final String INSERT_FAVORITE_GYM = "INSERT INTO favoritegym (gym_id,user_id) VALUES (?,?)";
    private static final String GET_FAVORITE_BY_USER = "SELECT gym.id, gym.address, gym.name, gym.province, gym.region, favoritegym.id FROM gym LEFT JOIN favoritegym ON favoritegym.gym_id = gym.id WHERE favoritegym.user_id=?";
    private static final String DELETE_FAVORITE_GYM = "DELETE FROM favoritegym WHERE user_id=? AND gym_id=?";

    @Override
    public long createFavoriteGym(FavoriteGym favoriteGym) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - createFavoriteGym");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_FAVORITE_GYM, Statement.RETURN_GENERATED_KEYS);) {
            st.setLong(1,favoriteGym.getGymId());
            st.setLong(2,favoriteGym.getUser());
            st.execute();

            try (ResultSet result = st.getGeneratedKeys();) {
                if (result.next()) {
                    return result.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return 0;

    }

    @Override
    public List<Gym> getAllFavoriteGym(long idUser) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - getAllFavoriteGym");

        List<Gym> favoriteGyms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FAVORITE_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    Gym gym = new Gym();
                    gym.setId(rs.getLong(1));
                    gym.setName(rs.getString(3));
                    gym.setRegion(rs.getString(5));
                    gym.setProvince(rs.getString(4));
                    gym.setAddress(rs.getString(2));

                    favoriteGyms.add(gym);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return favoriteGyms;
    }

    @Override
    public void deleteFavoriteGym(long idUser, long idGym) throws ServiceException {
        System.out.println("[SERVICE] FavoriteGym - deleteFavoriteGym");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FAVORITE_GYM);) {

            st.setLong(1,idUser);
            st.setLong(2,idGym);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

}
