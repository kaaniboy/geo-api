package model;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;

    private int userId;
    private int fbUserId;
    private String fbAccessToken;
}
