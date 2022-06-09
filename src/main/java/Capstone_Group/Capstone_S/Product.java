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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Products", uniqueConstraints= {@UniqueConstraint(name="UK_Product_PartNbr", columnNames="PartNbr")})
public class Product
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id; 

	@Column(length=30, nullable=false)
    private String PartNbr; 

	@Column(length=30, nullable=false)
    private String Name; 
           
	@Column(name = "Price", columnDefinition = "decimal(11,2)")
    private BigDecimal Price; 

	@Column(length=30, nullable=false)
    private String Unit; 

	@Column(length=255, nullable=true)
    private String PhotoPath; 

    private int VendorId;	

   
   // public virtual Vendor? Vendor { get; set; }
    
    @ManyToOne
    @JoinColumn(name="VendorId",insertable=false, updatable=false)
    @JsonIgnore
    private Vendor Vendor;

   // public List<RequestLine>? RequestLines { get; set; }
	
    @OneToMany(mappedBy="Id")
	private List<RequestLine> RequestLines; 
    
    // constructors
    
    public Product() {}

    public Product(int id, String partNbr, String name, BigDecimal price, String unit, String photoPath, int vendorId)
    {
	super();
	Id = id;
	PartNbr = partNbr;
	Name = name;
	Price = price;
	Unit = unit;
	PhotoPath = photoPath;
	VendorId = vendorId;	
	
    }

    // get / set
    
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPartNbr() {
		return PartNbr;
	}

	public void setPartNbr(String partNbr) {
		PartNbr = partNbr;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getPhotoPath() {
		return PhotoPath;
	}

	public void setPhotoPath(String photoPath) {
		PhotoPath = photoPath;
	}

	public int getVendorId() {
		return VendorId;
	}

	public void setVendorId(int vendorId) {
		VendorId = vendorId;
	} 
    
    
    
	
	
}
