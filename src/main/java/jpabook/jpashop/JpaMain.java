package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
                이 버전은 객체지향적이지 않은 코드이다.
                만약 주문번호 1번의 고객의 정보를 조회하고자 한다면 아래 코드와 같다.
             */

            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId);

            /*
                주문을 조회한 다음, 그 주문의 memberId를 받아와서, 다시 고객을 조회한다.
                객체지향 적인 코드를 사용한다면
                Member member = order.getMember(); 주문 정보에서 바로 받아올 수 있어야 한다.
             */

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
