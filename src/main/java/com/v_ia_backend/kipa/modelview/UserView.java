package com.v_ia_backend.kipa.modelview;

import com.v_ia_backend.kipa.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserView {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Long identification;
    private String position;
    private Timestamp creationDate;
    private StatusUser status;
    private Roles roleId;
    private String phoneNumber;


    public UserView(Users userDB) {
        this.id = userDB.getId();
        this.name = userDB.getName();
        this.lastName = userDB.getLastName();
        this.email = userDB.getEmail();
        this.identification = userDB.getIdentificationNumber();
        this.creationDate = userDB.getCreationDate();
        this.status = userDB.getStatus();
        this.roleId = userDB.getRoles();
    }
}
