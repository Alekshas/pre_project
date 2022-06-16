package com.example.pre_project.dao;

import com.example.pre_project.model.PromoteRequest;

import java.util.List;

public interface PromoteRequestDAO {

    List<PromoteRequest> getAllPromoteRequest();

    void add(PromoteRequest promoteRequest);

    void delete(PromoteRequest promoteRequest);

    void update(PromoteRequest promoteRequest);

    PromoteRequest getByUserID(Long id);

}
