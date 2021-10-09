package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


    @Entity
    @NamedQuery(name = "Hoppy.deleteAllRows", query = "DELETE from Hoppy")
    public class Hoppy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "hoppyList",cascade = CascadeType.PERSIST)
    private List<Person> persones;
    public List<Person> getPersones() {
        return persones;
    }
    public void setPersones(List<Person> persones) {
        this.persones = persones;
    }
    public Hoppy(String name) {
        this.name = name;
        this.persones = new ArrayList<>();
    }
    public void addPerson(Person person) {
        if(person!=null)
       try {
           this.persones.add(person);
           person.getHoppyList().add(this);
       }catch (Exception e)
       {
           e.getMessage();
       }
    }
    public void removePerson(Person person)
    {
        if(person!=null)
        {
          this.persones.remove(person);
          person.getHoppyList().remove(this);
        }
    }
    public Hoppy(String name, String description) {
        this.name = name;
        this.description = description;
    }

        public Hoppy(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public Hoppy() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hoppy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}