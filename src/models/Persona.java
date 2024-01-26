package models;

import customAnnotations.Table;

@Table(name="Persone")
public class Persona extends Entita implements Comparable<Persona> {
    private String nome;
    private String cognome;
    private String citta;
    private String indirizzo;
    
    
    public Persona() {
        super();
    }

    public Persona(String nome, String come, String citta, String indirizzo) {
        super();
        this.nome = nome;
        this.cognome = come;
        this.citta = citta;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return this.nome + "," + this.cognome + "," + this.citta + "," + this.indirizzo;
    }

    @Override
    public int compareTo(Persona persona) {
        int res = this.cognome.compareTo(persona.cognome);
        if (res == 0) {
            res = this.nome.compareTo(persona.nome);
        }
        return res;
    }

    @Override
    public String getFields() {
        return "Nome, Cognome, Citta, Indirizzo";
    }

    @Override
    public String getValues() {

        return
                (this.getId() == 0 ? "" : "'" + this.getId() + "',")
                        + "'" + this.getNome() + "',"
                        + "'" + this.getCognome() + "',"
                        + "'" + this.getCitta() + "',"
                        + "'" + this.getIndirizzo() + "'"
                ;
    }
}
