package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "invoice")
@XmlRootElement
public class Invoices extends BaseEntity {

    @Id
    private Integer id;

    @ManyToOne
    private User user;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object line;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerRef;

    private String syncToken;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    private String docNumber;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billEmail;

    private Date txnDate;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipFromAddr;

    private Date shipDate;
    private String trackingNum;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object classRef;

    private String printStatus;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object salesTermRef;

    private String txnSource;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object linkedTxn;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object DepositToAccountRef;

    private String globalTaxCalculation;
    private Boolean allowOnlineACHPayment;
    private String transactionLocationType;
    private Date dueDate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private String privateNote;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billEmailCc;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerMemo;

    private String emailStatus;
    private Double exchangeRate;
    private Double deposit;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object txnTaxDetail;

    private Boolean allowOnlineCreditCardPayment;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customField;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object departmentRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billEmailBcc;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipMethodRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billAddr;

    private Boolean applyTaxAfterDiscount;
    private Double homeBalance;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object deliveryInfo;

    private Double totalAmt;
    private String invoiceLink;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object recurDataRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object taxExemptionRef;

    private Double balance;
    private Double homeTotalAmt;
    private Boolean freeFormAddress;
    private Boolean allowOnlinePayment;
    private Boolean allowIPNPayment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
