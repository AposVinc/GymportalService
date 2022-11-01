package it.univaq.gymportal.model;

import java.util.Objects;

public class FavoriteGym {

	private long id;
	private long user;
	private long gym_id;

	public FavoriteGym() {
		this.id = 0;
		this.user = 0;
		this.gym_id = 0;
	}

	public FavoriteGym(long id, long user, long gym_id) {
		this.id = id;
		this.user = user;
		this.gym_id = gym_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
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
		if (!(o instanceof FavoriteGym)) return false;
		FavoriteGym that = (FavoriteGym) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUser(), getGymId());
	}
}
