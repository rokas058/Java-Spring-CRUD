package backend.project.controller;

import backend.project.dto.CustomerDto.CustomerFormDto;
import backend.project.dto.CustomerDto.CustomerReturnDto;
import backend.project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerReturnDto>> getAllCustomers() {
        List<CustomerReturnDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerReturnDto> getCustomerById(@PathVariable Long id) {
        CustomerReturnDto customer =  customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);

    }
    @PostMapping
    public ResponseEntity<CustomerReturnDto> createCustomer(@RequestBody CustomerFormDto customerDto) {
        CustomerReturnDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(createdCustomer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerReturnDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerFormDto customerDto) {
        CustomerReturnDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer with ID " + id + " deleted.");
    }
}
