import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import controller.*;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Esta clase es la principal donde inicializas tu programa y muestra un menu
 */
public class Main {

    /**
     * Este metodo sirve para crear el Manager de Entity que esta Anotado en las clase.
     * @return
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("JPAMagazines");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

        RolController rolController = new RolController(c, entityManagerFactory);
        CampeonController campeonController = new CampeonController(c, entityManagerFactory);


        Menu menu = new Menu();
        int opcio = menu.mainMenu();

        while (opcio > 0 && opcio < 14) {
            switch (opcio) {
                case 1:
                    List<Rol> roles = rolController.readRolFile("src/main/resources/lol.csv");
                    for (Rol r : roles) {
                        try {
                            rolController.addRol(r);
                        } catch (Exception e) {
                        }
                    }

                    List<Campeon> campeons = campeonController.readCampeonFile("src/main/resources/lol.csv");
                    for (Campeon campeon : campeons) {
                        campeonController.addCampeon(campeon);
                    }
                    break;
                case 2:
                    campeonController.showCampeonPorRol();
                    break;
                case 3:
                    campeonController.showCampeonCon();
                    break;
                case 4:
                    campeonController.showCampeonPor();
                    break;
                case 5:
                    campeonController.modificarCampeon();
                    break;
                case 6:
                    rolController.modificarRol();
                    break;
                case 7:
                    campeonController.borrarCampeon();
                    break;
                case 8:
                    campeonController.borrarCampeonPorRol();
                    break;
                case 9:
                    System.out.println("----------------------");
                    System.out.println("Crear Rol");
                    System.out.println("----------------------");

                    System.out.println("Rol:");
                    String rol = sc.nextLine().toUpperCase(Locale.ROOT);

                    rolController.addRol(new Rol(rol));

                    break;
                case 10:
                    System.out.println("----------------------");
                    System.out.println("Crear Campeon");
                    System.out.println("----------------------");

                    System.out.println("Nombre:");
                    String nom = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Elige un rol:");
                    String role = menu.RolMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

                    System.out.println("Historia:");
                    String historia = sc.nextLine();

                    campeonController.addCampeon(new Campeon(nom, new Rol(role), historia));

                    break;
                case 11:
                    campeonController.showCampeon();
                    break;
                case 12:
                    rolController.showRols();
                    break;
                case 13:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Introdueixi una de les opcions anteriors");
                    break;
            }
            opcio = menu.mainMenu();
        }
    }
}