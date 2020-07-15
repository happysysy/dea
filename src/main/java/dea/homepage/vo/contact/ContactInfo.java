package dea.homepage.vo.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ContactInfo {

    private String name;
    private String email;
    private String title;
    private String contents;
    private String phone;

    @Builder
    public ContactInfo( String name, String email, String title, String contents, String phone ) {
        this.name = name;
        this.email = email;
        this.title = title;
        this.contents = contents;
        this.phone = phone;
    }

}
