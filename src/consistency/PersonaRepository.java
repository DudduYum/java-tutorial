package consistency;

import models.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class PersonaRepository extends Repository<Persona> {
    public PersonaRepository(){
        super();
    }

    @Override
    protected void getFields(Persona object, ResultSet result) throws SQLException {
        super.getFields(object, result);

         object.setNome(result.getString("Nome"));
         object.setCognome(result.getString("Cognome"));
         object.setCitta(result.getString("Citta"));
         object.setIndirizzo(result.getString("Indirizzo"));
    }

    @Override
    protected List<Persona> getObject(ResultSet result) {
        List<Persona> listPersone = new ArrayList<Persona>();
        try {
            while (result.next()) {
                Persona persona = new Persona();
                listPersone.add(persona);
                this.getFields(persona, result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return listPersone;
    }

    @Override
    protected String getValue(Persona[] objects) {
           String value = Arrays.stream(objects).map(
                persona -> "(" + persona.getValues() + "),"
                        ).reduce("", String::concat);

        StringBuilder b = new StringBuilder(value);
        b.replace(value.lastIndexOf(","), value.lastIndexOf(",") + 1, "" );
        return value;
    }

    @Override
    protected String getCreateColumn() {
        return "Nome, Cognome, Citta, Indirizzo";
    }

    @Override
    protected String getEditColumn() {
        return "Id, Nome, Cognome, Citta, Indirizzo";
    }
}
