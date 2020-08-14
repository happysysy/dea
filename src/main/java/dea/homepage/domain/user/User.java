package dea.homepage.domain.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor( access =  AccessLevel.PROTECTED )
@AllArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long oid;

    private String id;

    private String password;

    private String name;

    //관리자 또는 사용자
    //@OneToMany( cascade=CascadeType.ALL, fetch=FetchType.EAGER )
    private String role;

    //  가입일
    @CreationTimestamp
    private LocalDateTime regdate;

    public User(String id, String password, String name, String role ) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
