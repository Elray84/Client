public class Data {

    private String nom;
    private int date;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "nom='" + nom + '\'' +
                ", date=" + date +
                '}';
    }
}
