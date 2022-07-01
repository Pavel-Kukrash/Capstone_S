package Capstone_Group.Capstone_S;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vendors")
public class VendorController 
{
	
		
			@Autowired 
			private VendorRepository vendorRepo;
									
			@GetMapping
			public List<Vendor> GetVendor()
			{
			    return vendorRepo.findAll();
			}
			
			@GetMapping("{id}")
			public Optional<Vendor> GetVendorById(@PathVariable int id)
			{
			    Optional<Vendor> p = vendorRepo.findById(id);
			    if (p.isPresent())
			        return p;
			    else
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}	
				
				
			@PostMapping
		    @ResponseStatus(HttpStatus.CREATED)
		    public Vendor createVendor(@RequestBody Vendor vendor) 
			{        
		        try 
		        {
		            return vendorRepo.save(vendor);
		        }
		        catch (Exception e)
		        {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		        }
		    }

			@PutMapping("/{id}")
		    public Vendor updateVendor(@PathVariable("id") int id,
		    @RequestBody Vendor updatedVendor) 
			{
		        Optional<Vendor> p = vendorRepo.findById(id);
		        if (p.isPresent())
		        {
		            Vendor oldVendor = p.get();
		            oldVendor.setCode(updatedVendor.getCode());
		            oldVendor.setName(updatedVendor.getName());
		            oldVendor.setAddress(updatedVendor.getAddress());
		            oldVendor.setCity(updatedVendor.getCity());
		            oldVendor.setState(updatedVendor.getState());
		            
		            		            
		            try 
		            {
		                vendorRepo.save(oldVendor);
		                return oldVendor;
		            }
		            
		            catch (Exception e)
		            {
		                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		            }
		        }
		            else
		            {
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		            }
			}
			
			@DeleteMapping("/{id}")
		    public void DeleteVendor(@PathVariable int id)
		    {
		        try 
		        {
		            vendorRepo.deleteById(id);
		        }
		        
		        catch (Exception e)
		        {
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		        }
		    }
			
			
		}

	
	

