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
}
