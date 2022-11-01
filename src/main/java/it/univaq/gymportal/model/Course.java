package it.univaq.gymportal.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Course {

	private long id;
	private String name;
	private String description;
	private String code;
	private long gym_id;

//	@JsonIgnore
	private List<FeedbackCourse> feedbackCourse;
//	@JsonIgnore
	private List<FavoriteCourse> favoriteCourse;

	public Course() {
		this.id = 0;
		this.name = "";
		this.description = "";
		this.code = "";
		this.gym_id = 0;
	}

	public Course(long id, String name, String description, String code, long gym_id) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.code = code;
		this.gym_id = gym_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FeedbackCourse> getFeedbackCourse() {
		return feedbackCourse;
	}

	public void setFeedbackCourse(List<FeedbackCourse> feedbackCourse) {
		this.feedbackCourse = feedbackCourse;
	}

	public List<FavoriteCourse> getFavoriteCourse() {
		return favoriteCourse;
	}

	public void setFavoriteCourse(List<FavoriteCourse> favoriteCourse) {
		this.favoriteCourse = favoriteCourse;
	}

	public long getGymId() {
		return gym_id;
	}

	public void setGymId(long gym_id) {
		this.gym_id = gym_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Course)) return false;
		Course course = (Course) o;
		return getId() == course.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getCode());
	}
}
