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
@RequestMapping("/api/requests")
public class RequestController 
{
			
					@Autowired 
					private RequestRepository requestRepo;
											
					@GetMapping
					public List<Request> GetRequest()
					{
					    return requestRepo.findAll();
					}
					
					@GetMapping("{id}")
					public Optional<Request> GetRequestById(@PathVariable int id)
					{
					    Optional<Request> p = requestRepo.findById(id);
					    if (p.isPresent())
					        return p;
					    else
					        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
					}	
						
						
					@PostMapping
				    @ResponseStatus(HttpStatus.CREATED)
				    public Request createRequest(@RequestBody Request request) 
					{        
				        try 
				        {
				            return requestRepo.save(request);
				        }
				        catch (Exception e)
				        {
				            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				        }
				    }

					@PutMapping("/{id}")
				    public Request updateRequest(@PathVariable("id") int id,
				    @RequestBody Request updatedRequest) 
					{
				        Optional<Request> p = requestRepo.findById(id);
				        if (p.isPresent())
				        {
				        	Request oldData = p.get();
				            oldData.setDescription(updatedRequest.getDescription());
				            try 
				            {
				            	requestRepo.save(oldData);
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
				    public void DeleteRequest(@PathVariable int id)
				    {
				        try 
				        {
				        	requestRepo.deleteById(id);
				        }
				        
				        catch (Exception e)
				        {
				            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				        }
				    }
					
					
				}

	

