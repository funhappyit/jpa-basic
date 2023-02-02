package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.metal.MetalMenuBarUI;
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

            saveWithCascade(em);
            tx.commit();
        }catch (Exception e){
           tx.rollback();
           e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }


    //부모 1명에 자식 2명을 저장
    private static void saveNoCascade(EntityManager em){

        //부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        //1번 자식 저장
        Child child1 = new Child();
        child1.setParent(parent); // 자식 -> 부모 연관관계 설정
        parent.getChildren().add(child1); // 부모 -> 자식
        em.persist(child1);

        //2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
    }

    private static void saveWithCascade(EntityManager em){
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent); //연관관계 추가
        child2.setParent(parent); //연관관계 추가
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        //부모 저장, 연관된 자식들 저장
        em.persist(parent);




    }





}
