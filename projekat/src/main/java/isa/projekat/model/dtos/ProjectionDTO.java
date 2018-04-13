package isa.projekat.model.dtos;

import java.util.Date;

public class ProjectionDTO {

	private long id;

	private String name;
	
	private String description;
	
	private long projectionDateTime;
	
	private String movieRating;
	
	private long moviePerformanceId;
	
	private String moviePerformanceName;
	
	private long hallId;
	
	private String hallName;
	
	private long theatreCinemaId;
	
	private String theatreCinemaName;
	
	public ProjectionDTO() {
		
	}

	

	public ProjectionDTO(long id, String name, String description, long projectionDateTime, String movieRating,
			long moviePerformanceId, String moviePerformanceName, long hallId, String hallName, long theatreCinemaId,
			String theatreCinemaName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.projectionDateTime = projectionDateTime;
		this.movieRating = movieRating;
		this.moviePerformanceId = moviePerformanceId;
		this.moviePerformanceName = moviePerformanceName;
		this.hallId = hallId;
		this.hallName = hallName;
		this.theatreCinemaId = theatreCinemaId;
		this.theatreCinemaName = theatreCinemaName;
	}



	public long getProjectionDateTime() {
		return projectionDateTime;
	}



	public void setProjectionDateTime(long projectionDateTime) {
		this.projectionDateTime = projectionDateTime;
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

	public long getMoviePerformanceId() {
		return moviePerformanceId;
	}

	public void setMoviePerformanceId(long moviePerformanceId) {
		this.moviePerformanceId = moviePerformanceId;
	}

	public String getMoviePerformanceName() {
		return moviePerformanceName;
	}

	public void setMoviePerformanceName(String moviePerformanceName) {
		this.moviePerformanceName = moviePerformanceName;
	}

	public long getHallId() {
		return hallId;
	}

	public void setHallId(long hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public long getTheatreCinemaId() {
		return theatreCinemaId;
	}

	public void setTheatreCinemaId(long theatreCinemaId) {
		this.theatreCinemaId = theatreCinemaId;
	}

	public String getTheatreCinemaName() {
		return theatreCinemaName;
	}

	public void setTheatreCinemaName(String theatreCinemaName) {
		this.theatreCinemaName = theatreCinemaName;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}
	
	
}
