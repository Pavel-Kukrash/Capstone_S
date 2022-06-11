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
@RequestMapping("/api/products")
public class ProductController 
{
	
			
				@Autowired 
				private ProductRepository productRepo;
										
				@GetMapping
				public List<Product> GetProduct()
				{
				    return productRepo.findAll();
				}
				
				@GetMapping("{id}")
				public Optional<Product> GetProductById(@PathVariable int id)
				{
				    Optional<Product> p = productRepo.findById(id);
				    if (p.isPresent())
				        return p;
				    else
				        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				}	
					
					
				@PostMapping
			    @ResponseStatus(HttpStatus.CREATED)
			    public Product createProduct(@RequestBody Product product) 
				{        
			        try 
			        {
			            return productRepo.save(product);
			        }
			        catch (Exception e)
			        {
			            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			        }
			    }

				@PutMapping("/{id}")
			    public Product updateProduct(@PathVariable("id") int id,
			    @RequestBody Product updatedProduct) 
				{
			        Optional<Product> p = productRepo.findById(id);
			        if (p.isPresent())
			        {
			        	Product oldData = p.get();
			            oldData.setName(updatedProduct.getName());
			            try 
			            {
			            	productRepo.save(oldData);
			                return oldData;
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
			    public void DeleteProduct(@PathVariable int id)
			    {
			        try 
			        {
			        	productRepo.deleteById(id);
			        }
			        
			        catch (Exception e)
			        {
			            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			        }
			    }
				
				
			}

	
	

