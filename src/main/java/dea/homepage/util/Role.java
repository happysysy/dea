package dea.homepage.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    MEMBER( "ROLE_MEMBER"),
    MANAGER( "ROLE_ADMIN" );

    private String value;
}
