package testcsv;

public class Persona implements Comparable<Persona> {
    private final String nome;
    private final String cognome;
    private final String citta;
    private final String indirizzo;


    public Persona(String nome, String come, String citta, String indirizzo) {
        this.nome = nome;
        this.cognome = come;
        this.citta = citta;
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return this.nome + "," + this.cognome + "," + this.citta + "," + this.indirizzo;
    }

    public String getNome() {
        return nome;
    }


    public String getCognome() {
        return cognome;
    }

    public String getCitta() {
        return citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public int compareTo(Persona persona) {
        int res = this.cognome.compareTo(persona.cognome);
        if (res == 0) {
            res = this.nome.compareTo(persona.nome);
        }
        return res;
    }
}
