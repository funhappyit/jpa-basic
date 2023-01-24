package hellojpa;

import javax.persistence.*;

//자식
@Entity
public class Child {

    @EmbeddedId
   private ChildId id;

    @MapsId("parentId") //ChildId.parendId 매핑
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    public Parent parent;

    private String name;
}

