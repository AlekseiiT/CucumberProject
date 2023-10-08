package org.example.api.pojos.subunit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private int otherEmployeeCount;
    private int unassignedEmployeeCount;
    private int totalSubunitCount;
}
