package com.poe.crm.api;

import com.poe.crm.api.dto.ClientDTO;
import com.poe.crm.api.dto.ClientMapper;
import com.poe.crm.business.Client;
import com.poe.crm.business.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api") // on peut ne pas mettre cette ligne. dans ce cas pas de /api dans l'url
public class ClientController {

    @Autowired
    CrmService crmService;

    @GetMapping("clients")
    public List<ClientDTO> getAll() {

        List<ClientDTO> json = new ArrayList<>();
        for(Client client: crmService.getAllClients()){
            ClientDTO clientDTO = ClientMapper.convertToDto(client);
            clientDTO.setTotalExpense(crmService.calculateExpense(client.getId()));
            json.add(clientDTO);
        }
        return json;
    }


// SANS DTO
//    @GetMapping("clients")
//    public List<Client> getAll() {
//        return crmService.getAllClients();
//    }

    @PostMapping("clients")
    public void createClient(@RequestBody Client client) {
        crmService.addClient(client);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable("id") Long id){
        Optional<Client> o = crmService.findClient(id);
        if(o.isPresent()){
            Client client = o.get();
            ClientDTO clientDTO = ClientMapper.convertToDto(client);
            clientDTO.setTotalExpense(crmService.calculateExpense(client.getId()));
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // SANS DTO
//    @GetMapping("clients/{id}")
//    public ResponseEntity<Client> findClientById(@PathVariable("id") Long id) {
//        Optional<Client> o = crmService.findClient(id);
//        if (o.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(o.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    @DeleteMapping("clients/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean hasDeleted = crmService.deleteClient(id);
        if(hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("L'identifiant ne correspond ?? aucun Client");
        }
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Client client){

        if(! id.equals( client.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("L'identifiant dans URL ne correspond ?? identifiant dans Body");
        } else {
            if(crmService.updateClient(client)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body("L'identifiant ne correspond ?? aucun Client");
            }
        }
    }

    @GetMapping("searchbycompanyname")
    public List<Client> searchByCompanyName(@RequestParam(value ="companyname") String companyName){
        return crmService.searchByCompanyName(companyName);
    }

    @GetMapping("searchbyfirstnameandlastname")
    public List<Client> searchByFirstNameAndLastName(@RequestParam(value ="firstname") String firstName,@RequestParam(value ="lastname") String lastName){
        return crmService.searchByFirstNameAndLastName(firstName, lastName);
    }
}