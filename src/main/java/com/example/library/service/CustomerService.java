package com.example.library.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.exceptions.BookNotFound;
import com.example.library.exceptions.UserDoesNotExist;
import com.example.library.exceptions.currentlyNoBookAvailableException;
import com.example.library.exceptions.newMaxLimitReachedException;
import com.example.library.model.Books;
import com.example.library.model.Customer;
import com.example.library.model.UserBook;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.CustomerRepository;
import com.example.library.repository.UserBookRepository;

@Service
public class CustomerService
{

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private UserBookRepository userbookrepo;

    @Autowired
    private BooksRepository bookrepo;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    CustomerService(CustomerRepository custRepo, UserBookRepository userbookrepo, BooksRepository bookrepo)
    {
        this.custRepo     = custRepo;
        this.bookrepo     = bookrepo;
        this.userbookrepo = userbookrepo;
    }

    public Customer createNewCustomer(Customer cust)
    {
        return this.custRepo.save(cust);
    }

    public List<Customer> getAllCustomers()
    {
        List<Customer> custs = new ArrayList<Customer>();
        this.custRepo.findAll().forEach(new Consumer<Customer>()
        {

            @Override
            public void accept(Customer t)
            {
                custs.add(t);
            }
        });
        return custs;
    }

    public UserBook lendBookToCustomer(Integer custId, Books book)
        throws UserDoesNotExist, newMaxLimitReachedException, currentlyNoBookAvailableException
    {

        Optional<Customer> custOpt = this.custRepo.findById(custId);
        List<Books>        books   = this.bookrepo.getAllBooksWithIsbn(book.getIsbn());
        if (!custOpt.isPresent())
        {
            throw new UserDoesNotExist();
        }

        if (books.size() < 1)
        {
            throw new BookNotFound();
        }
        else
        {

        }

        Customer cust = custOpt.get();
        if (cust.getBooksRemaining() > 0)
        {
            int numOfBooksAssigned = this.userbookrepo.getBorrowedBooksWithIsbn(book.getIsbn()).size();
            if (numOfBooksAssigned < book.getNumofCopies())
            {

                // Reduce book quota for user
                cust.setBooksRemaining(cust.getBooksRemaining() - 1);
                this.custRepo.save(cust);

                // Create an assignment entry
                UserBook usrbk = new UserBook(cust, book, book.getIsbn());
                return this.userbookrepo.save(usrbk);

            }
            else
            {
                LOGGER.error("{} was not available",book.getBookTitle());
                throw new currentlyNoBookAvailableException();
            }
        }
        else
        { 
            LOGGER.error("Customer Id {} has reached max limit",cust.getCustomerId());
            throw new newMaxLimitReachedException();
        }
    }

    public List<Books> getBooksAssignedToUser(Integer custId)
    {
        List<UserBook> usrbooks = this.userbookrepo.findBooksByUserId(custId);

        UserBook[]      arr   = usrbooks.toArray(new UserBook[0]);
        int             bookid;
        Optional<Books> optbook;
        List<Books>     books = new ArrayList<Books>();
        for (int i = 0; i < arr.length; i++)
        {
           
            bookid  = arr[i].getBook().getBookId();
            optbook = this.bookrepo.findById(bookid);
            if (optbook.isPresent())
            {
                books.add(optbook.get());
            }
        }
        return books;

    }

}
