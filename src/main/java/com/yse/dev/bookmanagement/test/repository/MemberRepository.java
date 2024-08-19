package com.yse.dev.bookmanagement.test.repository;

import com.yse.dev.bookmanagement.test.dto.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext //이 어노테이션은 EntitiyManager를 스프링 빈으로 등록해준다.
    private EntityManager em;

    // id를 반환해주는 이유는 CQS 이론 때문 -> 이 부분은 추후에 문서화 해서 작성할 예정
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
