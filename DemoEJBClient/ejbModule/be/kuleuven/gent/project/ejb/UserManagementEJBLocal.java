package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import model.Filiaal;

@Local
public interface UserManagementEJBLocal {

	public Filiaal findFiliaal(String login);

}
