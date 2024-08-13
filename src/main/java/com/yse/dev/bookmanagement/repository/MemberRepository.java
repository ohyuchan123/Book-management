package com.yse.dev.bookmanagement.repository;

import com.yse.dev.bookmanagement.dto.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext //이 어노테이션은 EntitiyManager를 스프링 빈으로 등록해준다.
    public EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
