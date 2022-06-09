package Capstone_Group.Capstone_S;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RequestLines")
public class RequestLine 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id; 
	
	
	private int RequstId;
	
	
	private int ProductId; 	
    	
    @Min(value=0, message="must be equal or greater than 0")
    @ColumnDefault("1")
	private int Quantity;    

    
    
    //public virtual Product Product

    @ManyToOne
    @JoinColumn(name="ProductId",insertable=false, updatable=false)
    @JsonIgnore
    private Product Products;
   
    //public virtual Request Request 
    
    @ManyToOne
    @JoinColumn(name="RequestId",insertable=false, updatable=false)
    @JsonIgnore
    private Request Requests;
    
	
	//constructors
    
    public RequestLine() {}


	public RequestLine(int id, int requstId, int productId, int quantity) {
		super();
		Id = id;
		RequstId = requstId;
		ProductId = productId;
		Quantity = quantity;
	}


	// get / set
	
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public int getRequstId() {
		return RequstId;
	}


	public void setRequstId(int requstId) {
		RequstId = requstId;
	}


	public int getProductId() {
		return ProductId;
	}


	public void setProductId(int productId) {
		ProductId = productId;
	}


	public int getQuantity() {
		return Quantity;
	}


	public void setQuantity(int quantity) {
		Quantity = quantity;
	} 
    
  
	
	
    
	
	
}
