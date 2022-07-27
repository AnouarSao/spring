package com.poe.crm.business.service;

import com.poe.crm.business.Client;
import com.poe.crm.business.Order;
import com.poe.crm.dao.ClientRepository;
import com.poe.crm.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CrmService {

    /*******************************************************/
    /** Client **/
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Optional<Client> findClient(Long id) {
        return clientRepository.findById(id);
    }
    public boolean deleteClient(Long id){
        if(clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    public boolean updateClient(Client client) {
        if(clientRepository.existsById(client.getId())){
            clientRepository.save(client);
            return true;
        }else {
            return false;
        }
    }

    /*******************************************************/
    /** Order **/

    @Autowired
    OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findOrder(Long id) {
        return orderRepository.findById(id);
    }

    public boolean deleteOrder(Long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public boolean updateOrder(Order order) {
        if(orderRepository.existsById(order.getId())){
            orderRepository.save(order);
            return true;
        }else {
            return false;
        }
    }

    public List<Client>searchByCompanyName(String companyName){
        return clientRepository.findAllByCompanyName(companyName);
    }

    public List<Client>searchByFirstNameAndLastName(String firstName, String lastName){
        return clientRepository.findAllByFirstNameAndLastName(firstName,lastName );
    }
    public float calculateExpense(Long clientId){
        float total = 0;
        Optional<Client> op = clientRepository.findById(clientId);
        if(op.isPresent()){
            Client client = op.get();
            for(Order order : client.getOrders()){
                total = total + order.getNbDays()*order.getUnitPrice();
            }
        }
        return total;
    }
}




//CRM SERVICE SANS UTILISER JPAREPOSITORY
//    ArrayList<Client> clients = new ArrayList<>();
//    long index = 0;
//
//
//        public List<Client> getAllClients() {
//        return clients;
//    }
//
//
//        public void addClient(Client client){
//        index++;
//        client.setId(index);
//        clients.add(client);
//    }
//
//
//    public Optional<Client> findClient(Long id) {
//        for (Client client : clients) {
//            if (client.getId().equals(id)) {
//                return Optional.of(client);
//            }
//        }
//        return Optional.empty();
//    }
//
//
//    public boolean deleteClient(Long id) {
//        Iterator<Client> it = clients.iterator();
//        while (it.hasNext()) {
//            Client client = it.next();
//            if (client.getId().equals(id)) {
//                it.remove();
//                return true;
//
//                //break;  // Continue la boucle pour tester l'accès concurrentiel
//            }
//        }
//        return false;
//
///*        for(Client client: clients){
//            if(client.getId().equals(id)){
//                clients.remove(client);
//                break;
//            }
//        }*/
//    }
//
//
//    public boolean updateClient(Client client) {
//
//        for (Client c : clients) {
//            if (c.getId().equals(client.getId())) {
//                clients.remove(c);
//                clients.add(client);
//                return true;
//            }
//        }
//        return false;
//    }


