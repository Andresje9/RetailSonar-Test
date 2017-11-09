package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Filiaal;

/**
 * Session Bean implementation class UserManagementEJB
 */
@Stateless
public class UserManagementEJB implements UserManagementEJBLocal {
	
	@PersistenceContext(unitName="demodb")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserManagementEJB() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public Filiaal findFiliaal(String id) {
		
		Query q = em.createQuery("SELECT f FROM Filiaal f WHERE f.id = :id");
		q.setParameter("id", id);
		List<Filiaal> persons = q.getResultList();
		
		if(persons.size() == 1)
			return persons.get(0);
		else return null;
	}

}
