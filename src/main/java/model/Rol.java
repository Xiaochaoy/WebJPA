package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para anotar el objeto de rol con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "rol")
public class Rol implements Serializable {
    @Id
    @Column(name = "rol",length = 20)
    private String rol;

    /**
     * Esto para pillar rol.
     * @return devuelve el rol.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Esto para asignar un rol.
     * @param rol recibe el que le vas a poner a el rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Este metodo sirve para decir de que formato para mostrar.
     * @return un frase
     */
    @Override
    public String toString() {
        return "Roles{" +
                "rol='" + rol + '\'' +
                '}';
    }

    /**
     * Este es el constructor de la clase
     * @param rol recibe un rol
     */
    public Rol(String rol){
        this.rol = rol;
    }

    /**
     * Es un constructor
     */
    public Rol(){
        super();
    }

}
