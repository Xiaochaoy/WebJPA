package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para anotar el objeto de campeon con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "campeon")
public class Campeon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "nom",length = 20)
    String name;

    @ManyToOne
    @JoinColumn(name = "rol")
    Rol rol;

    @Column(name = "historia")
    String historia;


    /**
     * Este es el constructor de la clase
     * @param name recibe un nombre
     * @param rol recibe un rol
     * @param historia recibe una historia
     */
    public Campeon(String name, Rol rol, String historia){
        super();
        this.name = name;
        this.rol = rol;
        this.historia = historia;
    }

    /**
     * Es un constructor
     */
    public Campeon(){
        super();
    }

    /**
     * Esto para pillar la id.
     * @return devuelve la id.
     */
    public int getId() {
        return id;
    }

    /**
     * Esto para asignar una id.
     * @param id recibe el que le vas a poner a la id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Esto para pillar el nombre.
     * @return devuelve el nombre.
     */
    public String getName() {
        return name;
    }

    /**
     * Esto para asignar un nombre.
     * @param name recibe el que le vas a poner a lla nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * Esto para asignar una historia.
     * @param historia recibe el que le vas a poner a la historia.
     */
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    /**
     * Esto para pillar rol.
     * @return devuelve el rol.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Esto para asignar un rol.
     * @param rol recibe el que le vas a poner a el rol.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Este metodo sirve para decir de que formato para mostrar.
     * @return un frase
     */
    @Override
    public String toString() {
        return "Campeon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rol='" + rol.toString() + '\'' +
                ", historia='" + historia + '\'' +
                '}';
    }
}
