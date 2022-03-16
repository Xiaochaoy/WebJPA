package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "rol")
public class Rol implements Serializable {
    @Id
    @Column(name = "rol",length = 20)
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "rol='" + rol + '\'' +
                '}';
    }

    public Rol(String rol){
        this.rol = rol;
    }
}
