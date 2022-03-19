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
 * Esta clase sirve para controlar la tabla campeon situada en mi base de datos
 */
public class CampeonController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Campeon> campeons ;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public CampeonController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        campeons = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de Campeon
     * @throws IOException
     */
    public List<Campeon> readCampeonFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, "\"");
            campeons.add(new Campeon(listToken.get(0),new Rol(listToken.get(2)),listToken.get(4)));
        }

        return campeons;
    }

    /**
     * Para añadir campeon
     * @param campeon recibe un campeon y lo añade
     */
    public void addCampeon(Campeon campeon) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(campeon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar campeones
     */
    public void showCampeon(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery("from Campeon", Campeon.class).getResultList();
        for (Campeon campeon : result) {
            System.out.println(campeon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar campeones por roles
     */
    public void showCampeonPorRol(){
        String rol = menu.RolMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        String sql = "from Campeon where rol='" + rol + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery(sql,Campeon.class).getResultList();
        for (Campeon campeon : result) {
            System.out.println(campeon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar campeones con un texto especificado
     */
    public void showCampeonCon(){
        System.out.println("Escribe el texto a contener: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Campeon where nom like '%" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery(sql, Campeon.class).getResultList();
        for (Campeon campeon : result) {
            System.out.println(campeon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar campeones que empiezan por tal letra
     */
    public void showCampeonPor(){
        System.out.println("Escribe la letra de inicio: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Campeon where nom like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery(sql, Campeon.class).getResultList();
        for (Campeon campeon : result) {
            System.out.println(campeon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las id y los nombres de campeon que hay
     */
    public void showCampeonNom(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery("from Campeon order by id", Campeon.class).getResultList();
        for (Campeon campeon : result) {
            System.out.println(campeon.getId() + " " + campeon.getName());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar el nombre de un campeon
     */
    public void modificarCampeon() {
        int id = menu.NomMenu(connection,entityManagerFactory);
        System.out.println("Escribe el nuevo nombre: ");
        String newNom = sc.nextLine().toUpperCase(Locale.ROOT);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Campeon campeon = (Campeon) em.find(Campeon.class, id);
        campeon.setName(newNom);
        em.merge(campeon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar un campeon
     */
    public void borrarCampeon() {
        int id = menu.NomMenu(connection,entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Campeon campeon = (Campeon) em.find(Campeon.class, id);
        em.remove(campeon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar campeones por roles
     */
    public void borrarCampeonPorRol() {
        String rol = menu.RolMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        String sql = "from Campeon where rol='" + rol + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Campeon> result = em.createQuery(sql,Campeon.class).getResultList();
        for (Campeon campeon : result) {
            em.remove(campeon);
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
