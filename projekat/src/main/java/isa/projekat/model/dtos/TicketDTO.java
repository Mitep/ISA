package isa.projekat.model.dtos;

public class TicketDTO {

	
	private long id;
	
	private int price;

	private int discount;

	private int seat_row;
	
	private int seat_col;
	
	private String projectionName;
	
	private long projectionTime;
	
	private long projectionDate;
	
	private String segmentName;
	
	private String hallName;
	
	private String movieName;

	public TicketDTO(long id, int price, int discount, int seat_row, int seat_col, String projectionName,
			String segmentName, String hallName) {
		super();
		this.id = id;
		this.price = price;
		this.discount = discount;
		this.seat_row = seat_row;
		this.seat_col = seat_col;
		this.projectionName = projectionName;
		this.segmentName = segmentName;
		this.hallName = hallName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public int getSeat_col() {
		return seat_col;
	}

	public void setSeat_col(int seat_col) {
		this.seat_col = seat_col;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public long getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(long projectionTime) {
		this.projectionTime = projectionTime;
	}

	public long getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(long projectionDate) {
		this.projectionDate = projectionDate;
	}
	
}
