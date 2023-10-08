package org.example.api.pojos.subunit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datum {
    private Subunit subunit;
    private int count;
}
