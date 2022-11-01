package it.univaq.gymportal.model;

import java.util.Objects;

public class FavoriteCourse {

	private long id;
	private long user_id;
	private long course_id;

	public FavoriteCourse() {
		this.id = 0;
		this.user_id = 0;
		this.course_id = 0;
	}

	public FavoriteCourse(long id, long user_id, long course_id) {
		this.id = id;
		this.user_id = user_id;
		this.course_id = course_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return user_id;
	}

	public void setUserId(long user_id) {
		this.user_id = user_id;
	}

	public long getCourseId() {
		return course_id;
	}

	public void setCourseId(long course_id) {
		this.course_id = course_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FavoriteCourse)) return false;
		FavoriteCourse that = (FavoriteCourse) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUserId(), getCourseId());
	}
}
