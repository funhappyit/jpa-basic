package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //db당 하나만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //고객시 요청이 올때마다 엔티티 매니저는 쓰레드 간에 공유해서는 안됀다.
        EntityManager em = emf.createEntityManager();
        /*
            1) 엔티티 메니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
            2) 엔티티 메니저는 쓰레드간에 공유X(사용하고 버려야 한다.) 데이터 베이스 커넥션을 쓰고 버림
            3) JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

            JPQL 소개
            1) 가장 단순한 조회 방법
                -> EntityManager.find()
                -> 객체 그래프 탐색(a.getB().getC())
        */
        //transaction 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
        //    Member findMember = em.find(Member.class, 1L);
        //    findMember.setName("HelloJPA"); member객체를 대상으로 쿼리를 짬
            //from 뒤의 대상이 테이블이 아닌 객체이여야 한다. member객체를 다가져와 객체를 기준으로 하는 객체 지향 쿼리
            /*
            JPQL
            -> JPA를 사용하면 엔티티 객체를 중심으로 개발
            -> 문제는 검색 쿼리
            -> 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            -> 모든 DB데이터를 객체로 변환해서 검색하는 것은 불가능
            -> 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요요

            JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
            SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
            JPQL은 엔티티 객체를 대상으로 쿼리
            SQL은 데이터베이스 테이블을 대상으로 쿼리



             */


            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = "+member.getName());
            }

            tx.commit();
        }catch (Exception e){
           tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
