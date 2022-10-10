package it.univaq.gymportal.business;

import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FeedbackGym;

import java.util.List;

public interface FeedbackGymService {

    long createFeedbackGym (FeedbackGym feedbackGym) throws ServiceException;
    FeedbackGym getFeedback(long id) throws ServiceException;
    List<FeedbackGym> getAllFeedbackByGym(long id) throws ServiceException;
    List<FeedbackGym> getAllFeedbackByUser(long id) throws ServiceException;
    void deleteFeedbackGym(long id) throws ServiceException;
    void updateFeedbackGym(FeedbackGym feedbackGym) throws ServiceException;
}
