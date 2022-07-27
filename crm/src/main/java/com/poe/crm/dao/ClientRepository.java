package com.poe.crm.dao;

import com.poe.crm.business.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public List<Client> findAllByCompanyName(String companyName);
    public List<Client> findAllByFirstNameAndLastName(String firstName, String lastName);

}
