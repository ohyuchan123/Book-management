package com.yse.dev.bookmanagement.repository;

import com.yse.dev.bookmanagement.dto.Member;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
// junit4 에서는 RunWith(SpringRunner.class)를 사용
// junit5 에서는 Extendwith(SPringExtension.class)를 사용
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSave() {
        // given
        Member member = new Member();
        member.setUsername("John");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        System.out.println("findMember.getFirstName() = " + (findMember == member));
    }
}
