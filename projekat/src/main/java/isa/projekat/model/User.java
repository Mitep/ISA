package isa.projekat.model;

import java.util.Set;

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

@Entity
@Table(name = "user")
public class User {

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
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Ticket> tickets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<AmbientRating> ambientRatings;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ProjectionRating> projectionRatings;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
	private Set<FriendRequest> sentRequests;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reciever")
	private Set<FriendRequest> recievedRequests;
	
	//ko su moji prijatelji
	@ManyToMany
    @JoinTable(name = "friendship",
    joinColumns = @JoinColumn(name = "user1_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "user2_id", nullable = false))
	private Set<User> myFriends;
	//kome sam ja prijatelj
	@ManyToMany(mappedBy = "myFriends")
	private Set<User> friendsWith;
	*/
	public User() {
		super();
	}

	public User(String email, String userPassword, String userPasswordConf,String userName,
			String userSurname,String city, String mobileNumber, UserRole userRole, boolean userStatus) {
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
/*
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

	public Set<FriendRequest> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(Set<FriendRequest> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public Set<FriendRequest> getRecievedRequests() {
		return recievedRequests;
	}

	public void setRecievedRequests(Set<FriendRequest> recievedRequests) {
		this.recievedRequests = recievedRequests;
	}

	public Set<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(Set<User> myFriends) {
		this.myFriends = myFriends;
	}

	public Set<User> getFriendsWith() {
		return friendsWith;
	}

	public void setFriendsWith(Set<User> friendsWith) {
		this.friendsWith = friendsWith;
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
	
	
	
	
	
}
