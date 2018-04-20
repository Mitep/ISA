package isa.projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seat_id", nullable = false)
	private long id;
	
	@Column(name = "row_num", nullable = false)
	private int rowNum;

	@Column(name = "col_num", nullable = false)
	private int colNum;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="segment_id")
	@JsonIgnore
	private Segment segment;
	
	
	@ManyToMany
	@JoinTable(name = "projekcijeSjedista",
	joinColumns = @JoinColumn(name = "sjediste", nullable = false),
	inverseJoinColumns = @JoinColumn(name = "projekcija", nullable = false))
	@JsonIgnore
	private List<Projection> listaProjekcija;
	
	public Seat() {
		this.listaProjekcija = new ArrayList<Projection>();
	}

	public List<Projection> getListaProjekcija() {
				return listaProjekcija;
		 	}
		
	public void setListaProjekcija(List<Projection> listaProjekcija) {
				this.listaProjekcija = listaProjekcija;
			}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public void setId(long id) {
		this.id = id;
	}

}
