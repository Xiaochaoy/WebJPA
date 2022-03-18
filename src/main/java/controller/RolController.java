package controller;

import model.Article;
import model.Rol;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RolController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Rol> roles ;

    public RolController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        roles = new ArrayList<>();
    }

    public List<Rol> readRolFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, "\"");
             roles.add(new Rol(listToken.get(2)));
        }

        return roles;
    }

    public void addRol(Rol rol) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(rol);
        em.getTransaction().commit();
        em.close();
    }

    private static List<String> getTokenList(String string, String delimiters) {

        List<String> listTokens = new ArrayList<String>();

        try {

            StringTokenizer st = new StringTokenizer(string, delimiters);

            while( st.hasMoreTokens() ) {

                //add token to the list
                listTokens.add( st.nextToken() );
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return listTokens;
    }
}
