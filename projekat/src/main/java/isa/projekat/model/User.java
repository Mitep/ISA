package isa.projekat.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@NotNull
	private String email;
	
	@NotNull
	private String userPassword;
	
	@NotNull
	private String userPasswordConf;
	
	@NotNull
	private  String userName;
	
	@NotNull
	private String userSurname;
	
	@NotNull
	private String city;
	
	@NotNull
	private String mobileNumber;
	
	@NotNull
	private UserRole userRole;
	

	@NotNull
	private boolean userStatus;
	
	@NotNull
	private boolean firstLogin;
	
	@NotNull
	private int brojLogovanja;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Ticket> tickets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<AmbientRating> ambientRatings;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ProjectionRating> projectionRatings;
	*/
	//ko su moji prijatelji
	@ManyToMany
    @JoinTable(name = "friendship",
    joinColumns = @JoinColumn(name = "user1_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "user2_id", nullable = false))
	@JsonIgnore
	private List<User> myFriends;
	
	//kome sam ja prijatelj
	@ManyToMany(mappedBy = "myFriends")
	@JsonIgnore
	private List<User> friendsWith;
	
	//zahtjevi
	@ManyToMany
    @JoinTable(name = "friendshipRequest",
    joinColumns = @JoinColumn(name = "sender", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "reciever", nullable = false))
	@JsonIgnore
	private List<User> friendsRequest;
		
	@ManyToMany(mappedBy = "adminiBioPoz")
	@JsonIgnore
	private List<TheatreCinema> bioPozAdmini;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "korisnik")
	@JsonIgnore
	private List<RequestOglasa> zahtjeviOglasa;
	
	@ManyToMany(mappedBy = "listaKorisnika")
	@JsonIgnore
	private List<TheatreCinema> listaTC;
	
	
	private MedalType mt;
	
	public User() {
		super();
	}

	public User(String email, String userPassword, String userPasswordConf,String userName,
			String userSurname,String city, String mobileNumber, UserRole userRole, boolean userStatus, boolean firstLogin) {
		super();
		this.email = email;
		this.userPassword = userPassword;
		this.userPasswordConf = userPasswordConf;
		this.userName = userName;
		this.userSurname = userSurname;
		this.city = city;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
		this.userStatus = userStatus;
		this.firstLogin = firstLogin;
		this.myFriends = new ArrayList<User>();
		this.friendsWith = new ArrayList<User>();
		this.friendsRequest = new ArrayList<User>();
		this.bioPozAdmini = new ArrayList<TheatreCinema>();
		this.zahtjeviOglasa = new ArrayList<RequestOglasa>();
		this.listaTC = new ArrayList<TheatreCinema>();
	}

	public List<TheatreCinema> getListaTC() {
		return listaTC;
	}

	public void setListaTC(List<TheatreCinema> listaTC) {
		this.listaTC = listaTC;
	}

	public MedalType getMt() {
		return mt;
	}

	public void setMt(MedalType mt) {
		this.mt = mt;
	}

	public int getBrojLogovanja() {
		return brojLogovanja;
	}

	public void setBrojLogovanja(int brojLogovanja) {
		this.brojLogovanja = brojLogovanja;
	}

	public List<RequestOglasa> getZahtjeviOglasa() {
		return zahtjeviOglasa;
	}

	public void setZahtjeviOglasa(List<RequestOglasa> zahtjeviOglasa) {
		this.zahtjeviOglasa = zahtjeviOglasa;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUserPasswordConf() {
		return userPasswordConf;
	}

	public void setUserPasswordConf(String userPasswordConf) {
		this.userPasswordConf = userPasswordConf;
	}
	
public List<TheatreCinema> getBioPozAdmini() {
		return bioPozAdmini;
	}

	public void setBioPozAdmini(List<TheatreCinema> bioPozAdmini) {
		this.bioPozAdmini = bioPozAdmini;
	}

	/*
 *
	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Set<AmbientRating> getAmbientRatings() {
		return ambientRatings;
	}

	public void setAmbientRatings(Set<AmbientRating> ambientRatings) {
		this.ambientRatings = ambientRatings;
	}

	public Set<ProjectionRating> getProjectionRatings() {
		return projectionRatings;
	}

	public void setProjectionRatings(Set<ProjectionRating> projectionRatings) {
		this.projectionRatings = projectionRatings;
	}
*/
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	public List<User> getFriendsWith() {
		return friendsWith;
	}

	public void setFriendsWith(List<User> friendsWith) {
		this.friendsWith = friendsWith;
	}

	public List<User> getFriendsRequest() {
		return friendsRequest;
	}

	public void setFriendsRequest(List<User> friendsRequest) {
		this.friendsRequest = friendsRequest;
	}

	
	
	
}
