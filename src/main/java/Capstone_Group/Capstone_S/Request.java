package Capstone_Group.Capstone_S;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

//import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Requests")
public class Request 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int Id; 

	@Column(length=80, nullable=false)
     private String Description; 

	@Column(length=80, nullable=false)
     private String Justification;

	@Column(length=80, nullable=true)
     private String RejectionReason; 

	@Column(length=20, nullable=false)	
     private String DeliveryMode = "Pickup"; 

	@Column(length=20, nullable=false)	   
	private String Status = "NEW"; 

	@Column(name = "Total", columnDefinition = "decimal(11,2)", nullable=true)		
    private BigDecimal Total; 
     
     private int UserId;	

    
     @ManyToOne
     @JoinColumn(name="UserId",insertable=false, updatable=false)
     @JsonIgnore     
     private User Users;
     
    	
     @OneToMany(mappedBy="Id")
    // @Nullable
 	 private List<RequestLine> RequestLines;
     
    // constructors
     
     public Request() {}

	 public Request(int id, String description, String justification, String rejectionReason, String deliveryMode,
			String status, BigDecimal total, int userId) {
		super();
		Id = id;
		Description = description;
		Justification = justification;
		RejectionReason = rejectionReason;
		DeliveryMode = deliveryMode;
		Status = status;
		Total = total;
		UserId = userId;
	}
	
	// get / set 
	 
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getJustification() {
		return Justification;
	}

	public void setJustification(String justification) {
		Justification = justification;
	}

	public String getRejectionReason() {
		return RejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		RejectionReason = rejectionReason;
	}

	public String getDeliveryMode() {
		return DeliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		DeliveryMode = deliveryMode;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public BigDecimal getTotal() {
		return Total;
	}

	public void setTotal(BigDecimal total) {
		Total = total;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	} 
     
     
	      
     
     
     
     
}
