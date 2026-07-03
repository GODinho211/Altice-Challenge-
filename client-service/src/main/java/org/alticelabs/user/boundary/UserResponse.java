package org.alticelabs.user.boundary;

import java.util.UUID;

public class UserResponse {
    public UUID id;
    public String name;
    public String email;
    public boolean active;

    public UserResponse(UUID id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }
}
