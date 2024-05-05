package com.socgen.creditcardbackend.controller;
import com.socgen.creditcardbackend.exception.InvalidUserDetails;
import com.socgen.creditcardbackend.model.Approver;
import com.socgen.creditcardbackend.model.Customer;
import com.socgen.creditcardbackend.model.LoginUser;
import com.socgen.creditcardbackend.model.User;
import com.socgen.creditcardbackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/approver/login")
    public ResponseEntity<Approver> loginAsApprover(@RequestParam String userId,@RequestParam String rawPassword)
    {
        Approver approverUser = null;
        LoginUser user = new LoginUser(userId,rawPassword);
        try{
            approverUser = userService.loginAsApprover(user);
        }
        catch(InvalidUserDetails e){
            return new ResponseEntity<Approver>((Approver) null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Approver>(approverUser,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/customer/login")
    public ResponseEntity<Customer> loginAsCustomer(@RequestParam String userId,@RequestParam String rawPassword)
    {
        Customer customerUser= null;
        LoginUser user = new LoginUser(userId,rawPassword);
        try{
            customerUser = userService.loginAsCustomer(user);
        }
        catch(InvalidUserDetails e){
            return new ResponseEntity<Customer>((Customer) null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Customer>(customerUser,HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Iterable<User>> allUsers()
    {
        return new ResponseEntity<Iterable<User>>(userService.allUsers(),HttpStatus.OK);
    }


}
