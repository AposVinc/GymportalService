package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FeedbackCourse;

import java.util.List;

public interface FeedbackCourseService {

    long createFeedbackCourse (FeedbackCourse feedbackCourse) throws ServiceException;
    FeedbackCourse getFeedback(long id) throws ServiceException;
    List<FeedbackCourse> getAllFeedbackByCourse(long id) throws ServiceException;
    List<FeedbackCourse> getAllFeedbackByUser(long id) throws ServiceException;
    void deleteFeedbackCourse(long id) throws ServiceException;
    void updateFeedbackCourse(FeedbackCourse feedbackCourse) throws ServiceException;

}
