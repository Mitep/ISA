package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medalje")
public class RatingScale implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medalje_id", nullable = false)
	private Integer medaljeId;
	
	@NotNull
	private String odBronzana;
	
	@NotNull
	private String doBronzana;
	
	@NotNull
	private String odSrebrna;
	
	@NotNull
	private String doSrebrna;
	
	@NotNull
	private String odZlatna;
	
	@NotNull
	private String doZlatna;

	public RatingScale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingScale(@NotNull String odBronzana, @NotNull String doBronzana, @NotNull String odSrebrna,
			@NotNull String doSrebrna, @NotNull String odZlatna, @NotNull String doZlatna) {
		super();
		this.odBronzana = odBronzana;
		this.doBronzana = doBronzana;
		this.odSrebrna = odSrebrna;
		this.doSrebrna = doSrebrna;
		this.odZlatna = odZlatna;
		this.doZlatna = doZlatna;
	}

	public String getOdBronzana() {
		return odBronzana;
	}

	public void setOdBronzana(String odBronzana) {
		this.odBronzana = odBronzana;
	}

	public String getDoBronzana() {
		return doBronzana;
	}

	public void setDoBronzana(String doBronzana) {
		this.doBronzana = doBronzana;
	}

	public String getOdSrebrna() {
		return odSrebrna;
	}

	public void setOdSrebrna(String odSrebrna) {
		this.odSrebrna = odSrebrna;
	}

	public String getDoSrebrna() {
		return doSrebrna;
	}

	public void setDoSrebrna(String doSrebrna) {
		this.doSrebrna = doSrebrna;
	}

	public String getOdZlatna() {
		return odZlatna;
	}

	public void setOdZlatna(String odZlatna) {
		this.odZlatna = odZlatna;
	}

	public String getDoZlatna() {
		return doZlatna;
	}

	public void setDoZlatna(String doZlatna) {
		this.doZlatna = doZlatna;
	}


	
	

	
}
