package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

import java.sql.Date;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class PaymentFormDataDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Date createDate;
    private Date lastUpdate;
    private Long rentalId;
    private Integer amount;
    private List<PaymentFormMetaDataDTO> paymentFormMetaData;

    private PaymentFormDataDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public Integer getAmount() {
        return amount;
    }

    public List<PaymentFormMetaDataDTO> getPaymentFormMetaData() {
        return paymentFormMetaData;
    }

    public static final class PaymentFormDataDTOBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Date createDate;
        private Date lastUpdate;
        private Long rentalId;
        private Integer amount;
        private List<PaymentFormMetaDataDTO> paymentFormMetaData;

        private PaymentFormDataDTOBuilder() {
        }

        public static PaymentFormDataDTOBuilder aPaymentFormDataDTO() {
            return new PaymentFormDataDTOBuilder();
        }

        public PaymentFormDataDTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PaymentFormDataDTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PaymentFormDataDTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PaymentFormDataDTOBuilder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public PaymentFormDataDTOBuilder withLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public PaymentFormDataDTOBuilder withRentalId(Long rentalId) {
            this.rentalId = rentalId;
            return this;
        }

        public PaymentFormDataDTOBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public PaymentFormDataDTOBuilder withPaymentFormMetaData(List<PaymentFormMetaDataDTO> paymentFormMetaData) {
            this.paymentFormMetaData = paymentFormMetaData;
            return this;
        }

        public PaymentFormDataDTO build() {
            PaymentFormDataDTO paymentFormDataDTO = new PaymentFormDataDTO();
            paymentFormDataDTO.amount = this.amount;
            paymentFormDataDTO.paymentFormMetaData = this.paymentFormMetaData;
            paymentFormDataDTO.lastName = this.lastName;
            paymentFormDataDTO.createDate = this.createDate;
            paymentFormDataDTO.lastUpdate = this.lastUpdate;
            paymentFormDataDTO.rentalId = this.rentalId;
            paymentFormDataDTO.email = this.email;
            paymentFormDataDTO.firstName = this.firstName;
            return paymentFormDataDTO;
        }
    }
}
