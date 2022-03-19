package controller;

import model.Campeon;
import model.Rol;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

/**
 * Esta clase sirve para controlar la tabla rol situada en mi base de datos
 */
public class RolController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Rol> roles ;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public RolController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        roles = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de Rol
     * @throws IOException
     */
    public List<Rol> readRolFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, "\"");
             roles.add(new Rol(listToken.get(2)));
        }

        return roles;
    }

    /**
     * Para añadir rol
     * @param rol
     */
    public void addRol(Rol rol) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(rol);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar roles
     */
    public void showRols(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Rol> result = em.createQuery("from Rol", Rol.class).getResultList();
        for (Rol rol : result) {
            System.out.println(rol.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar el rol de los campeones
     */
    public void modificarRol(){
        String rol = menu.RolMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        System.out.println("Escribe la primera letra del campeon que quieras modificar ?");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "from Campeon where nom like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Campeon> result = em.createQuery(sql, Campeon.class).getResultList();
        for (Campeon campeon : result) {
            campeon.setRol(new Rol(rol));
            em.merge(campeon);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para dividir una frase en trozos depediendo del separador
     * @param string recibe una frase
     * @param delimiters recibe cual es el separador
     * @return devuelve un array de palabras separadas.
     */
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
