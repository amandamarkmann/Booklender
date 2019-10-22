package se.lexicon.amanda.booklender.models;

import java.time.LocalDate;
import java.util.Objects;

public class LibraryUser {
	
	private int userId;
	private LocalDate regDate;
	private String name;
	private String email;
	
	
	public LibraryUser(int userId, LocalDate regDate, String name, String email) {
		this.userId = userId;
		this.regDate = regDate;
		this.name = name;
		this.email = email;
	}

	public LibraryUser(LocalDate regDate, String name, String email) {
		this.regDate = regDate;
		this.name = name;
		this.email = email;
	}
	
	
	public LibraryUser() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, regDate, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryUser other = (LibraryUser) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(regDate, other.regDate) && userId == other.userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LibraryUser [userId=");
		builder.append(userId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
	
}
