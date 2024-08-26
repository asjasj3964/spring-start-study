package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity // jpa가 관리하는 엔티티
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity 전략
    private Long id; // 데이터 구분을 위한 시스템에서 정하는 id
    // @Column(name = "username") // DB의 컬럼명이 username과 매핑된다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
