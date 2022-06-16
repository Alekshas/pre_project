package com.example.pre_project.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Getter
@NoArgsConstructor
@Entity
@Table(name = "promote_requests")
public class PromoteRequest {

    @Id
    @Column
    private Long userID;

    @Column
    private boolean wantToBeAdmin;


    public PromoteRequest(long id, boolean b) {
        this.userID = id;
        this.wantToBeAdmin = b;
    }

}
