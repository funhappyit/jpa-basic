package hellojpa;

import javax.persistence.*;

//부모
@Entity
public class Parent {

    @Id @Column(name="PARENT_ID")
    private String id;
    private String name;

}
