package Capstone_Group.Capstone_S;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

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
@RequestMapping("/api/users")
public class UserController 
{
	
		@Autowired 
		private UserRepository userRepo;
		
		@PersistenceContext   
		private EntityManager em;
		
		@GetMapping
		public List<User> GetUser()
		{
		    return userRepo.findAll();
		}
		
		@GetMapping("{id}")
		public Optional<User> GetUserById(@PathVariable int id)
		{
		    Optional<User> p = userRepo.findById(id);
		    if (p.isPresent())
		        return p;
		    else
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}		
		
		@SuppressWarnings("unchecked")
		@GetMapping("{username}/{password}")
		
		public User GetUserByLogin(@PathVariable String username, @PathVariable String password)
		{
			
		    List<User> users = em.createQuery("SELECT u FROM User u WHERE u.Username = :username AND u.Password = :password")
		    .setParameter("username", username)
		    .setParameter("password", password)
		    .getResultList();
						
		    if (users.size() == 1)
		        return users.get(0);
		    else
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}		
		
			
		@PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public User createUser(@RequestBody User user) 
		{        
	        try 
	        {
	            return userRepo.save(user);
	        }
	        catch (Exception e)
	        {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
	    }

		@PutMapping("/{id}")
	    public User updateUser(@PathVariable("id") int id,
	    @RequestBody User updatedUser) 
		{
	        Optional<User> p = userRepo.findById(id);
	        if (p.isPresent())
	        {
	            User oldUser = p.get();
	            oldUser.setUsername(updatedUser.getUsername());
	            oldUser.setPassword(updatedUser.getPassword());
	            oldUser.setFirstname(updatedUser.getFirstname());
	            oldUser.setLastname(updatedUser.getLastname());	                      
	            
	            try 
	            {
	                userRepo.save(oldUser);
	                return oldUser;
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
	            userRepo.deleteById(id);
	        }
	        
	        catch (Exception e)
	        {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	    }
		
		
	}

	
	

