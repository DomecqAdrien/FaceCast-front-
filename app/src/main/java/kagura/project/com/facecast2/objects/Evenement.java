package kagura.project.com.facecast2.objects;

public class Evenement {
	private String id;
	private String nom;
	private String date_debut;
	private String date_fin;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public String getNb_jours() {
		return nb_jours;
	}

	public void setNb_jours(String nb_jours) {
		this.nb_jours = nb_jours;
	}

	private String type;
	private String nb_jours;
	
	public Evenement(String id, String nom, String date_debut, String date_fin, String creator, String nb_jours) {
		super();
		this.id = id;
		this.nom = nom;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.type = type;
		this.nb_jours = nb_jours;
	}
	
	public Evenement() {
		super();
	}



	@Override
	public String toString() {
		return nom;
	}
}
