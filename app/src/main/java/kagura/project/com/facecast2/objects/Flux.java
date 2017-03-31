package kagura.project.com.facecast2.objects;



public class Flux {

    private String nom;
    private String adresse;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Flux(String adresse, String nom){
        this.adresse = adresse;
        this.nom = nom;

    }

    @Override
    public String toString() {
        return "Flux{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
