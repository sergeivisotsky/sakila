package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class PaymentFormData {
    private String firstName;
    private String lastName;
    private String email;
    private Date createDate;
    private Date lastUpdate;
    private Long rentalId;
    private Integer amount;
    private List<PaymentFormMetaData> paymentFormMetaData;

    private PaymentFormData() {
    }

    public List<PaymentFormMetaData> getPaymentFormMetaData() {
        return paymentFormMetaData;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPaymentFormMetaData(List<PaymentFormMetaData> paymentFormMetaData) {
        this.paymentFormMetaData = paymentFormMetaData;
    }

    public static final class PaymentFormDataBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private Date createDate;
        private Date lastUpdate;
        private Long rentalId;
        private Integer amount;
        private List<PaymentFormMetaData> paymentFormMetaData;

        public PaymentFormDataBuilder() {
        }

        public static PaymentFormDataBuilder aPaymentFormData() {
            return new PaymentFormDataBuilder();
        }

        public PaymentFormDataBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PaymentFormDataBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PaymentFormDataBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PaymentFormDataBuilder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public PaymentFormDataBuilder withLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public PaymentFormDataBuilder withRentalId(Long rentalId) {
            this.rentalId = rentalId;
            return this;
        }

        public PaymentFormDataBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public PaymentFormDataBuilder withPaymentFormMetaData(List<PaymentFormMetaData> paymentFormMetaData) {
            this.paymentFormMetaData = paymentFormMetaData;
            return this;
        }

        public PaymentFormData build() {
            PaymentFormData paymentFormData = new PaymentFormData();
            paymentFormData.amount = this.amount;
            paymentFormData.lastUpdate = this.lastUpdate;
            paymentFormData.paymentFormMetaData = this.paymentFormMetaData;
            paymentFormData.firstName = this.firstName;
            paymentFormData.lastName = this.lastName;
            paymentFormData.rentalId = this.rentalId;
            paymentFormData.createDate = this.createDate;
            paymentFormData.email = this.email;
            return paymentFormData;
        }
    }
}
