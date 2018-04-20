package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "offer")
public class Offer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id", nullable = false)
	private long offerId;
	
	@NotNull
	private String ponuda;
	
	@OneToOne
	private User receiver;
	
	@ManyToOne
	@JsonIgnore
	private User sender;

	@ManyToOne
	private Oglas ponudaOglas;
	

	public Offer() {
		super();
	}
	public Offer( String ponuda) {
		super();
		this.ponuda = ponuda;
	
	}
	
	
	public User getSender() {
		return sender;
	}



	public void setSender(User sender) {
		this.sender = sender;
	}




	public String getPonuda() {
		return ponuda;
	}

	public void setPonuda(String ponuda) {
		this.ponuda = ponuda;
	}


	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Oglas getPonudaOglas() {
		return ponudaOglas;
	}

	public void setPonudaOglas(Oglas ponudaOglas) {
		this.ponudaOglas = ponudaOglas;
	}


	public long getOfferId() {
		return offerId;
	}


	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	

}

