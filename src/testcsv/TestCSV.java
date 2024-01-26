/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testcsv;

import consistency.IRepository;
import consistency.PersonaRepository;
import models.Persona;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author rkostyuk
 */
public class TestCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        File fr = new File("C:\\Users\\rkostyuk\\Documents\\NetBeansProjects\\TestCSV\\src\\dati.csv");
//        FileWriter fileWriter = new FileWriter();

        Scanner sc = new Scanner(new File("C:\\Users\\rkostyuk\\Documents\\NetBeansProjects\\TestCSV\\src\\dati.csv"));

        sc.useDelimiter("\r\n");
        sc.next();

        ArrayList<Persona> listaPersone = new ArrayList<Persona>();

        while (sc.hasNext())  //returns a boolean value
        {
            System.out.print("------------------");

            String[] fields = sc.next().split(",");

            listaPersone.add(
                    new Persona(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3]
                    )
            );

            String wholeLine = Arrays.stream(fields).reduce("", String::concat);
            System.out.println(wholeLine);

            //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner

        System.out.println("---------------------");
        for (Persona p: listaPersone) {
            System.out.println(p.toString());
        }

        Collections.sort(listaPersone);
        System.out.println("++++++++++++++++++++++");
        for (Persona p: listaPersone) {
            System.out.println(p.toString());
        }


        IRepository<Persona> repository = new PersonaRepository();
        Persona[] arPersone = new Persona[listaPersone.size()];
        Iterator<Persona> pInterator = listaPersone.iterator();

        int cnt = 0;

        while(true){
            if (!pInterator.hasNext()) break;
            arPersone[cnt++] = pInterator.next();
        }

        repository.create(arPersone);
//        DbContext dbContext = new DbContext();
//
//        dbContext.WriteToDb(listaPersone);


        String summaryFileName = "./summary.txt";

        try {
            FileWriter myWriter = new FileWriter(summaryFileName);

            for (Persona p: listaPersone) {
                System.out.println(p.toString());
                myWriter.write(p.toString());
                myWriter.write('\n');
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
