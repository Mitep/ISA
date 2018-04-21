package isa.projekat.model.dtos;

public class SegmentDTO {

	private long hallId;
	
	private String segmentName;
	
	private long segmentId;
	
	public SegmentDTO() {}

	public long getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(long segmentId) {
		this.segmentId = segmentId;
	}

	public long getHallId() {
		return hallId;
	}

	public void setHallId(long hallId) {
		this.hallId = hallId;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	
}
