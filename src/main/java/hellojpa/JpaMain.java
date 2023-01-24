package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //db당 하나만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //고객시 요청이 올때마다 엔티티 매니저는 쓰레드 간에 공유해서는 안됀다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{


            tx.commit();
        }catch (Exception e){
           tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
