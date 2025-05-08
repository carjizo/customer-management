package com.customer.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricsDTO {

    private Integer totalUsers;
    private Integer minimumAge;
    private Integer maximumAge;
    private Integer averageAge;
    private Double standardDeviationAge;
}
