package alpha_power_system.quotation.mapper;

import alpha_power_system.quotation.dto.response.QuotationResponse;
import alpha_power_system.quotation.entity.Quotation;

public class QuotationMapper {

    private QuotationMapper() {
    }

    public static QuotationResponse toResponse(
            Quotation quotation) {

        return QuotationResponse.builder()
                .id(quotation.getId())
                .quotationNumber(quotation.getQuotationNumber())
                .customerId(quotation.getCustomer().getId())
                .customerName(quotation.getCustomer().getCustomerName())
                .leadId(quotation.getLead().getId())
                .productInterest(quotation.getLead().getProductInterest())
                .amount(quotation.getAmount())
                .gstAmount(quotation.getGstAmount())
                .discount(quotation.getDiscount())
                .finalAmount(quotation.getFinalAmount())
                .validTill(quotation.getValidTill())
                .remarks(quotation.getRemarks())
                .status(quotation.getStatus())
                .build();
    }
}