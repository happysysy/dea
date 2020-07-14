package dea.homepage.vo.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ContactInfo {

    private String name;
    private String email;
    private String title;
    private String contents;

    @Builder
    public ContactInfo( String name, String email, String title, String contents ) {
        this.name = name;
        this.email = email;
        this.title = title;
        this.contents = contents;
    }

}
