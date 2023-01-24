package hellojpa;

import javax.persistence.*;

//손자
@Entity
public class GrandChild {

    @EmbeddedId
   private GrandChildId id;


    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="PARENT_ID"),
            @JoinColumn(name="CHILD_ID")
    })
    private Child child;

    private String name;

}
