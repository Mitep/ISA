package isa.projekat.model.dtos;

public class CinemaDTO {

	private long id;
	
	private String name;
	
	private String adress;
	
	private String description;
	
	private String rating;
	
	public CinemaDTO() {}

	public CinemaDTO(long id, String name, String adress, String description, String rating) {
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.rating = rating;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
