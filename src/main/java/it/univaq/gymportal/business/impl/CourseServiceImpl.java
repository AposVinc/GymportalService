package it.univaq.gymportal.business.impl;

import it.univaq.gymportal.business.CourseService;
import it.univaq.gymportal.business.Service;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl extends Service implements CourseService {

    private static final String GET_ALL_COURSES_BY_GYMS = "SELECT * FROM course WHERE gym_id=?";
    private static final String GET_COURSES_BY_NAME = "SELECT * FROM course WHERE course.gym_id = ? AND course.name LIKE ?";
    private static final String GET_COURSE = "SELECT * FROM course WHERE course.id = ?";
    private static final String INSERT_COURSE = "INSERT INTO course (code,name,description,gym_id) VALUES (?,?,?,?)";
    private static final String UPDATE_COURSE = "UPDATE course SET code=?, name=?, description=? WHERE id=?";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE id=?";

    @Override
    public List<Course> getCoursesByGym(long gymId) throws ServiceException {
        System.out.println("[SERVICE] Course - getCoursesByGym");

        List<Course> courses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_ALL_COURSES_BY_GYMS);) {

            st.setLong(1,gymId);
            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    Course course = new Course();
                    course.setId(rs.getLong(1));
                    course.setCode(rs.getString(2));
                    course.setName(rs.getString(4));
                    course.setDescription(rs.getString(3));
                    course.setGymId(rs.getLong(5));

                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return courses;

    }

    @Override
    public List<Course> getCoursesByName(long gymId, String hint) throws ServiceException {
        System.out.println("[SERVICE] Course - getGymsByRegion");

        List<Course> courses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_COURSES_BY_NAME);) {

            st.setLong(1, gymId);
            st.setString(2, "%" + hint + "%");
            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    Course course = new Course();
                    course.setId(rs.getLong(1));
                    course.setCode(rs.getString(2));
                    course.setName(rs.getString(4));
                    course.setDescription(rs.getString(3));
                    course.setGymId(rs.getLong(5));

                    courses.add(course);
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return courses;
    }

    @Override
    public Course getCourse(long idCourse) throws ServiceException {
        System.out.println("[SERVICE] Course - getCourse");

        Course course = new Course();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_COURSE);) {

            st.setLong(1,idCourse);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    course.setId(rs.getLong(1));
                    course.setCode(rs.getString(2));
                    course.setName(rs.getString(4));
                    course.setDescription(rs.getString(3));
                    course.setGymId(rs.getLong(5));
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return course;
    }

    @Override
    public long createCourse(Course course) throws ServiceException {
        System.out.println("[SERVICE] Course - createCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_COURSE, Statement.RETURN_GENERATED_KEYS);) {

            st.setString(1, course.getCode());
            st.setString(2, course.getName());
            st.setString(3, course.getDescription());
            st.setLong(4,course.getGymId());
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
    public void updateCourse(Course course) throws ServiceException {
        System.out.println("[SERVICE] Course - updateCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(UPDATE_COURSE);) {

            st.setString(1, course.getCode());
            st.setString(2, course.getName());
            st.setString(3, course.getDescription());
            st.setLong(4, course.getId());
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @Override
    public void deleteCourse(long idCourse) throws ServiceException {
        System.out.println("[SERVICE] Course - deleteCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_COURSE);) {

            st.setLong(1,idCourse);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }
}
