package tasnimghanmi.grp1.callerapp;

public class Contact {
    public String nom,pseudo,num;

    public Contact() {

    }

    public Contact(String nom, String pseudo, String num) {
        this.nom = nom;
        this.pseudo = pseudo;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
