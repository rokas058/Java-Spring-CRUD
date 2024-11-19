package backend.project.service;

import backend.project.dto.CustomerDto.CustomerFormDto;
import backend.project.dto.CustomerDto.CustomerReturnDto;
import backend.project.entity.Customer;
import backend.project.exceptions.CustomerNotFoundException;
import backend.project.exceptions.DataIntegrityViolationException;
import backend.project.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<CustomerReturnDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerReturnDto.class))
                .toList();
    }

    public CustomerReturnDto getCustomerById(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        return modelMapper.map(existingCustomer, CustomerReturnDto.class);
    }

    public CustomerReturnDto createCustomer(CustomerFormDto customerDto) {
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new DataIntegrityViolationException("El. paštas jau užregistruotas: " + customerDto.getEmail());
        }

        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerReturnDto.class);
    }

    public CustomerReturnDto updateCustomer(Long id, CustomerFormDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        modelMapper.map(customerDto, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerReturnDto.class);
    }

    public void deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        customerRepository.delete(existingCustomer);
    }
}
