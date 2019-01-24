package models;

import lombok.Data;

@Data
public class Invite {
    private String firstName;
    private String lastName;

    private int userId;
    private int fbUserId;
    private String fbAccessToken;
}
