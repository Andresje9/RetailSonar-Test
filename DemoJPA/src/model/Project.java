package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProject;

	private String email;

	private String klantNaam;

	@Column(name="`telNr.`")
	private String telNr_;

	//bi-directional many-to-one association to Filiaal
	@OneToMany(mappedBy="project")
	private List<Filiaal> filiaals;

	public Project() {
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKlantNaam() {
		return this.klantNaam;
	}

	public void setKlantNaam(String klantNaam) {
		this.klantNaam = klantNaam;
	}

	public String getTelNr_() {
		return this.telNr_;
	}

	public void setTelNr_(String telNr_) {
		this.telNr_ = telNr_;
	}

	public List<Filiaal> getFiliaals() {
		return this.filiaals;
	}

	public void setFiliaals(List<Filiaal> filiaals) {
		this.filiaals = filiaals;
	}

	public Filiaal addFiliaal(Filiaal filiaal) {
		getFiliaals().add(filiaal);
		filiaal.setProject(this);

		return filiaal;
	}

	public Filiaal removeFiliaal(Filiaal filiaal) {
		getFiliaals().remove(filiaal);
		filiaal.setProject(null);

		return filiaal;
	}

}