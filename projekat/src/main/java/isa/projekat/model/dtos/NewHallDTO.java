package isa.projekat.model.dtos;

public class NewHallDTO {

	private long theatreCinemaId;
	
	private String hallName;
	
	public NewHallDTO() {}

	public long getTheatreCinemaId() {
		return theatreCinemaId;
	}

	public void setTheatreCinemaId(long theatreCinemaId) {
		this.theatreCinemaId = theatreCinemaId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	
}
