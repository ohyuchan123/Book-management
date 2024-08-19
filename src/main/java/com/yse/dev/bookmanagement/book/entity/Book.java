package com.yse.dev.bookmanagement.book.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


// JPA는 데이터베이스의 테이블을 객체처럼 다루는 ORM(Object Relation Mapping) 기술 중 하나입니다.
// 스프링 부트에서 JPA로 데이터를 다루기 위해 가장 먼저 하는 일은 엔티티 클래스를 만드는 것입니다.
// 엔티티 클래스는 데이터베이스 테이블과 1 : 1로 매핑되는 자바 클래스입니다.

@Entity // JPA가 해당 클래스를 엔티티로 인식하기 위해 사용하는 어노테이션
@Data // Lombok을 사용하여 Getter, Setter, toString, equals, hashCode 등을 자동으로 생성하는 어노테이션
@Builder // Lombok을 사용하여 Builder 패턴을 자동으로 생성하는 어노테이션
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 200)
    private String title;

    private Integer price;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    public Book() {

    }
}
