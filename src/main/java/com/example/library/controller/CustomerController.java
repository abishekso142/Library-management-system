package com.example.library.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.library.exceptions.BookNotFound;
import com.example.library.exceptions.currentlyNoBookAvailableException;
import com.example.library.exceptions.newMaxLimitReachedException;
import com.example.library.model.Books;
import com.example.library.model.Customer;
import com.example.library.model.UserBook;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.CustomerRepository;
import com.example.library.service.CustomerService;

@RestController
@RequestMapping("/Customers")
public class CustomerController
{

    @Autowired
    private CustomerService custService;

    CustomerController()
    {
        this.custService = custService;
    }

    @PostMapping
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer cust, UriComponentsBuilder uriComponentsBuilder)
        throws URISyntaxException
    {
        Customer      newCust       = this.custService.createNewCustomer(cust);
        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(newCust.getCustomerId());
        return ResponseEntity.created(new URI(uriComponents.getPath())).body(newCust);
    }

    @GetMapping
    public List<Customer> getAllCustomers()
    {
        return this.custService.getAllCustomers();
    }

    @PostMapping("/{custId}/Books")
    public void lendBookToCustomer(@RequestBody Books book, @PathVariable Integer custId) throws Exception
    {
        try
        {
            this.custService.lendBookToCustomer(custId, book);
        }
        catch (BookNotFound e)
        {

        }
        catch (currentlyNoBookAvailableException e)
        {

        }
        catch (newMaxLimitReachedException e)
        {

        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @GetMapping("/{custId}/Books")
    public List<Books> getAllBooksForUser(@PathVariable Integer custId){
        return this.custService.getBooksAssignedToUser(custId);
    }
}
