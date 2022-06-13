package com.example.pre_project.model;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "promote_requests")
public class PromoteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long UserID;

    @Column
    private String wantToBEAdmin;


}
