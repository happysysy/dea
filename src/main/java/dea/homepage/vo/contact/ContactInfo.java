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


}
