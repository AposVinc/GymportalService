package it.univaq.gymportal.business.impl;

import it.univaq.gymportal.business.FeedbackCourseService;
import it.univaq.gymportal.business.Service;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FeedbackCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCourseServiceImpl extends Service implements FeedbackCourseService {

    private static final String INSERT_FEEDBACK_COURSE = "INSERT INTO feedbackcourse (feed,rating,course_id,user_id) VALUES (?,?,?,?)";
    private static final String GET_FEEDBACK_BY_ID = "SELECT feedbackcourse.id, feed, rating, feedbackcourse.course_id, course.name, feedbackcourse.user_id, user.name, user.lastname FROM feedbackcourse JOIN course ON course.id = feedbackcourse.course_id JOIN user ON user.id = feedbackcourse.user_id WHERE feedbackcourse.id=?";
    private static final String GET_ALL_FEEDBACK_BY_COURSE = "SELECT feedbackcourse.id, feed, rating, feedbackcourse.course_id, course.name, feedbackcourse.user_id, user.name, user.lastname FROM feedbackcourse JOIN course ON course.id = feedbackcourse.course_id JOIN user ON user.id = feedbackcourse.user_id WHERE feedbackcourse.course_id=?";
    private static final String GET_ALL_FEEDBACK_BY_USER = "SELECT feedbackcourse.id, feed, rating, feedbackcourse.course_id, course.name, feedbackcourse.user_id, user.name, user.lastname FROM feedbackcourse JOIN course ON course.id = feedbackcourse.course_id JOIN user ON user.id = feedbackcourse.user_id WHERE feedbackcourse.user_id=?";
    private static final String DELETE_FEEDBACK_COURSE = "DELETE FROM feedbackcourse WHERE id=?";
    private static final String UPDATE_FEEDBACK_COURSE = "UPDATE feedbackcourse SET feed=?, rating=? WHERE id=?";

    @Override
    public long createFeedbackCourse(FeedbackCourse feedbackCourse) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - createFeedbackCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(INSERT_FEEDBACK_COURSE, Statement.RETURN_GENERATED_KEYS);) {

            st.setString(1,feedbackCourse.getFeed());
            st.setInt(2,feedbackCourse.getRating());
            st.setLong(3,feedbackCourse.getCourse());
            st.setLong(4,feedbackCourse.getUserId());

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
    public List<FeedbackCourse> getAllFeedbackByCourse(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - getAllFeedbackByCourse");

        List<FeedbackCourse> feedbackCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_ALL_FEEDBACK_BY_COURSE);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    FeedbackCourse feedbackCourse = new FeedbackCourse();
                    feedbackCourse.setId(rs.getLong(1));
                    feedbackCourse.setRating(rs.getInt(3));
                    feedbackCourse.setFeed(rs.getString(2));
                    feedbackCourse.setUserId(rs.getLong(6));
                    feedbackCourse.setUserName(rs.getString(7));
                    feedbackCourse.setUserLastname(rs.getString(8));
                    feedbackCourse.setCourse(rs.getLong(4));
                    feedbackCourse.setCourseName(rs.getString(5));

                    feedbackCourses.add(feedbackCourse);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackCourses;
    }

    @Override
    public FeedbackCourse getFeedback(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - getFeedbackCourse");

        FeedbackCourse feedbackCourse = new FeedbackCourse() ;
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_FEEDBACK_BY_ID);) {
            st.setLong(1,id);

            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()){
                    feedbackCourse.setId(rs.getLong(1));
                    feedbackCourse.setRating(rs.getInt(3));
                    feedbackCourse.setFeed(rs.getString(2));
                    feedbackCourse.setUserId(rs.getLong(6));
                    feedbackCourse.setUserName(rs.getString(7));
                    feedbackCourse.setUserLastname(rs.getString(8));
                    feedbackCourse.setCourse(rs.getLong(4));
                    feedbackCourse.setCourseName(rs.getString(5));
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackCourse;
    }

    @Override
    public List<FeedbackCourse> getAllFeedbackByUser(long idUser) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - getAllFeedbackByUser");

        List<FeedbackCourse> feedbackCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(GET_ALL_FEEDBACK_BY_USER);) {
            st.setLong(1,idUser);

            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()){
                    FeedbackCourse feedbackCourse = new FeedbackCourse();
                    feedbackCourse.setId(rs.getLong(1));
                    feedbackCourse.setRating(rs.getInt(3));
                    feedbackCourse.setFeed(rs.getString(2));
                    feedbackCourse.setUserId(rs.getLong(6));
                    feedbackCourse.setUserName(rs.getString(7));
                    feedbackCourse.setUserLastname(rs.getString(8));
                    feedbackCourse.setCourse(rs.getLong(4));
                    feedbackCourse.setCourseName(rs.getString(5));

                    feedbackCourses.add(feedbackCourse);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
        return feedbackCourses;
    }

    @Override
    public void deleteFeedbackCourse(long id) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - deleteFeedbackCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(DELETE_FEEDBACK_COURSE);) {

            st.setLong(1,id);
            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @Override
    public void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws ServiceException {
        System.out.println("[SERVICE] FeedbackCourse - updateFeedbackCourse");

        try (Connection connection = DriverManager.getConnection(urlDB, userDB, pswDB);
             PreparedStatement st = connection.prepareStatement(UPDATE_FEEDBACK_COURSE);) {

            st.setString(1, feedbackCourse.getFeed());
            st.setInt(2, feedbackCourse.getRating());
            st.setLong(3, feedbackCourse.getId());

            st.execute();

        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }
}
