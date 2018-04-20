package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "requestOglasa")
public class RequestOglasa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roId", nullable = false)
	private long roId;
	
	
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oglas_id")
	@JsonIgnore
	private Oglas oglas;
	
	@ManyToOne
	@JsonIgnore
	private User fanAdmin;
	
	@OneToOne
	@JsonIgnore
	private User sender;

	@NotNull
	private boolean prihvacen;

	public RequestOglasa() {}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}

	public User getFanAdmin() {
		return fanAdmin;
	}

	public void setFanAdmin(User fanAdmin) {
		this.fanAdmin = fanAdmin;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
	
}
