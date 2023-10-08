package org.example.api.pojos.subunit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootSubunit {
    private List<Datum> data;
    private Meta meta;
    private List<Object> rels;
}
