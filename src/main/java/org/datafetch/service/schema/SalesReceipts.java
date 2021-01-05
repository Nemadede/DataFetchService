package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
@Entity
@Table(name="salesreceipt")
public class SalesReceipts extends BaseEntity {

    @Id
    @Column(name="id", nullable = false)
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

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billEmail;
    private Date txnDate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipFromAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customField;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipDate;

    private String trackingNum;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object classRef;

    private String printStatus;
    private String paymentRefNum;
    private String txnSource;
    private String globalTaxCalculation;
    private String transactionLocationType;
    private Boolean applyTaxAfterDiscount;
    private String docNumber;
    private String privateNote;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object depositToAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerMemo;

    private String emailStatus;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object creditCardPayment;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object txnTaxDetail;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object paymentMethodRef;

    private Double exchangeRate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object departmentRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipMethodRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private Double homeBalance;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object deliveryInfo;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object recurDataRef;

    private Double totalAmt;
    private Double balance;
    private Double homeTotalAmt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
