package model;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rol")
    Rol rol;

    @Column(name = "historia")
    String historia;


    public Campeon(String name, Rol rol, String historia){
        super();
        this.name = name;
        this.rol = rol;
        this.historia = historia;
    }

    public Campeon(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

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
