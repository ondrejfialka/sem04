package cz.ucl.fa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Holiday
 * 
 */
@Entity
@NamedQueries( { 
 @NamedQuery(name = "Trip.getCount", query = "SELECT COUNT(h) FROM Holiday h WHERE h.fixed = true"),
 @NamedQuery(name = "Trip.getFixed", query = "SELECT h FROM Holiday h WHERE h.fixed = true")
})
public class Holiday implements Serializable {

	@Id
	private long id;
	private String name;
	private boolean fixed=true;
	
	@Temporal(TemporalType.DATE)
	private Date starts;
	
	@Temporal(TemporalType.DATE)
	private Date ends;
	
	private Double price;

	@ManyToMany
	private List<Transportation> transportation;

	@ManyToMany
	private Set<Service> services;

	@OneToMany(mappedBy = "holiday", cascade = CascadeType.ALL)
	@OrderBy("dayFrom ASC")
	private List<Stay> accommodation;

	private static final long serialVersionUID = 1L;

	public Holiday() {
		super();
		transportation = new ArrayList<Transportation>();
		accommodation = new ArrayList<Stay>();
		services = new HashSet<Service>();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getFixed() {
		return this.fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public List<Transportation> getTransportation() {
		return this.transportation;
	}

	public List<Stay> getAccommodation() {
		return this.accommodation;
	}

	public void addAccommodation(Stay stay) {
		accommodation.add(stay);
		stay.setHoliday(this);
	}

	public void addTransportation(Transportation transport) {
		transportation.add(transport);
	}

	public Set<Service> getServices() {
		return services;
	}

	public void addService(Service service) {
		services.add(service);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public void setAccommodation(List<Stay> accommodation) {
		this.accommodation = accommodation;
	}
	
	

}
