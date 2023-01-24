package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//손자 ID
@Embeddable
public class GrandChildId implements Serializable {
    private ChildId childId; //@MapsId("childId")로 매핑

    @Column(name="GRANDCHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildId that = (GrandChildId) o;
        return Objects.equals(childId, that.childId) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, id);
    }
}
