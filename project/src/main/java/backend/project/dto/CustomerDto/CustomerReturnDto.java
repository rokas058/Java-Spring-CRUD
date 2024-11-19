package backend.project.dto.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReturnDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
}
