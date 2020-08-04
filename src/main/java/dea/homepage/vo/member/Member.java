package dea.homepage.vo.member;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor( access =  AccessLevel.PROTECTED )
@AllArgsConstructor
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long oid;

    private String id;

    private String password;

    private String name;

    //관리자 또는 사용자
    private String role;

    public Member( String id, String password, String name, String role ) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }




}
