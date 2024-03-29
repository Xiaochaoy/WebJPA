package view;

import controller.CampeonController;
import controller.RolController;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;

    /**
     * Este es un constructor y llama a la clase padre suyo(nose quien es)
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar un menu
     * @return devuelte la opcion que elegiste en numero
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Rellenar Tablas");
            System.out.println("2. Mostrar los que sean ?");
            System.out.println("3. Mostrar los campeones que tengan un ?");
            System.out.println("4. Mostrar todos los campeon que empiezan por ?");
            System.out.println("5. Modificar el nombre de un campeon");
            System.out.println("6. Modificar el rols de los campeones que empiezan por ?");
            System.out.println("7. Eliminar un campeon");
            System.out.println("8. Eliminar campeones con el rol ?");
            System.out.println("9. Añadir un rol");
            System.out.println("10. Añadir un campeon");
            System.out.println("11. Mostrar campeones");
            System.out.println("12. Mostrar roles");
            System.out.println("13. Exit");
            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13);


        return option;
    }

    /**
     * Este metodo sirve para mostrar un menu de rol
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */
    public String RolMenu(Connection c, EntityManagerFactory entityManagerFactory){
        RolController rolController = new RolController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            rolController.showRols();
            System.out.println("Elige el rol: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }
    /**
     * Este metodo sirve para mostrar un menu de id y nombre de campeones
     * @param c recibe la coneccion
     * @return devuelve el nombre que elegiste
     */
    public int NomMenu(Connection c, EntityManagerFactory entityManagerFactory){
        CampeonController campeonController = new CampeonController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Campeones: ");
        for(;;){
            campeonController.showCampeonNom();
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return option;
        }
    }
}