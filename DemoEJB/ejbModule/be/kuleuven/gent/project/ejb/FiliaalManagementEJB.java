package be.kuleuven.gent.project.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import model.Filiaal;
import model.Project;

/**
 * Session Bean implementation class TaskManagementEJB
 */
@Stateless
public class FiliaalManagementEJB implements FiliaalManagementEJBLocal {

	@PersistenceContext(unitName="demodb")
	private EntityManager em;

	@EJB
	private UserManagementEJBLocal userEJB;

	@Resource
	private SessionContext ctx;

	/**
	 * Default constructor. 
	 */
	public FiliaalManagementEJB() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Filiaal findFiliaal(Long id) {
		return em.find(Filiaal.class, id);
	}
/*
	@Override
	public List<Task> findIncompletedPatientTasks(Person person) {
		Person p;
		if(person == null)
		{
			String login = ctx.getCallerPrincipal().getName();
			p = userEJB.findPerson(login);
		}else{
			p = person;
		}

		Query q = em.createQuery("SELECT t FROM Task t WHERE t.completed = 0 AND t.patient = :idPatient");
		q.setParameter("idPatient", p);
		List<Task> tasks = q.getResultList();
		return tasks;
	}
*/
	@Override
	public void updateFiliaal(Filiaal f) {
		em.merge(f);
	}


	@Override
	public void createFiliaal(Filiaal filiaal) {
		Filiaal f = filiaal;
		Query q = em.createQuery("INSERT INTO 'RetailSonarDB'.'Filiaal'"
							+ "(`idFiliaal`,`oppervlakte`,`omzet`,`Project_idProject`,`Adres`)"
							+ "(Id, oppervlakte, omzet, IdProject);");
		q.setParameter("Id", f.getId().getIdFiliaal());
		q.setParameter("oppervlakte", f.getOppervlakte());
		q.setParameter("omzet", f.getOmzet());
		q.setParameter("IdProject", f.getProject().getIdProject());
		
		q.executeUpdate();
	}


	@Override
	public void fileTaskReport(Filiaal filiaal) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Filiaal> findIncompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

}
