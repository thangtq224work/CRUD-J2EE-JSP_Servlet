package model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TuLanh")
@Entity
public class TuLanh {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,length = 30)
	private String brand;
	
	@Column(nullable = false,length = 30)
	private String color;
	
	@Column(nullable = false)
	private Float weight;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@Column(nullable = false)
	private Integer numOfWing;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Boolean isDeleted = true;
	
	@Column(nullable = false,columnDefinition = "NVARCHAR(50)")
	private String name;
	
	@Column(columnDefinition = "NVARCHAR(50)")
	private String description;
	
	@Column(nullable = false)
	private String createUser;
	
	@Column(nullable = false)
	private String lastModifieldUser;
	
	@Column(nullable = false)
	private Date createDate;
	
	@Column(nullable = false)
	private Date lastModifieldDate;
	
	public TuLanh() {
		// TODO Auto-generated constructor stub
	}
	public TuLanh(String brand, String color, Float weight, Integer capacity, Integer numOfWing, Long id,
			Integer quantity, BigDecimal price, Boolean isDeleted, String name, String description, String createUser,
			String lastModifieldUser, Date createDate, Date lastModifieldDate) {
		this.brand = brand;
		this.color = color;
		this.weight = weight;
		this.capacity = capacity;
		this.numOfWing = numOfWing;
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.isDeleted = isDeleted;
		this.name = name;
		this.description = description;
		this.createUser = createUser;
		this.lastModifieldUser = lastModifieldUser;
		this.createDate = createDate;
		this.lastModifieldDate = lastModifieldDate;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getNumOfWing() {
		return numOfWing;
	}

	public void setNumOfWing(Integer numOfWing) {
		this.numOfWing = numOfWing;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getquantity() {
		return quantity;
	}

	public void setquantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastModifieldUser() {
		return lastModifieldUser;
	}

	public void setLastModifieldUser(String lastModifieldUser) {
		this.lastModifieldUser = lastModifieldUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifieldDate() {
		return lastModifieldDate;
	}

	public void setLastModifieldDate(Date lastModifieldDate) {
		this.lastModifieldDate = lastModifieldDate;
	}
	@Override
	public String toString() {
		return "TuLanh [id=" + id + ", brand=" + brand + ", color=" + color + ", weight=" + weight + ", capacity="
				+ capacity + ", numOfWing=" + numOfWing + ", quantity=" + quantity + ", price=" + price + ", isDeleted="
				+ isDeleted + ", name=" + name + ", description=" + description + ", createUser=" + createUser
				+ ", lastModifieldUser=" + lastModifieldUser + ", createDate=" + createDate + ", lastModifieldDate="
				+ lastModifieldDate + "]";
	}
	

}
