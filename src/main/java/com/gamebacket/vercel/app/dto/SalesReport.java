package com.gamebacket.vercel.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesReport {
    private Double totalSales;
    private LocalDate dateCreated; // For daily report
    private Integer year;
    private Integer week;
}
