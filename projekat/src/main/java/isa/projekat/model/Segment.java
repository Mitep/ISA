package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Segment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "segment_id", nullable = false)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="hall_id")
	@JsonIgnore
	private Hall hall;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "segment")
	private Set<Seat> Seat;
	
	@Column(name = "row_num", nullable = true)
	private int rowNum;
	
	@Column(name = "col_num", nullable = true)
	private int colNum;
	
	public Segment() {
		
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Set<Seat> getSeat() {
		return Seat;
	}

	public void setSeat(Set<Seat> seat) {
		Seat = seat;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
