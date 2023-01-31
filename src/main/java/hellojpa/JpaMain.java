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

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember id = "+findMember.getId());



            tx.commit();
        }catch (Exception e){
           tx.rollback();
           e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

    }

    private static void printEmployee(Employee employee){
        System.out.println("employee = "+employee.getName());
    }

    private static void printEmployeeAndCompany(Employee employee){
        String name = employee.getName();
        System.out.println("name = "+name);

        String companyName = employee.getCompany().getName();
        System.out.println("company = "+companyName);

    }

}
