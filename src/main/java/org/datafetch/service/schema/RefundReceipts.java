package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="refundreceipt")
public class RefundReceipts extends BaseEntity {

    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @EmbeddedId
    private CompositeId compositeId;

    @MapsId(value = "user_id")
    @ManyToOne
    private User user;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object depositToAccountRef;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object line;

    private String syncToken;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object paymentRefNum;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billEmail;

    private Date TxnDate;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customField;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object classRef;

    private String printStatus;
    private String checkPayment;
    private String txnSource;
    private String globalTaxCalculation;
    private String transactionLocationType;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private String docNumber;
    private String privateNote;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerMemo;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object creditCardPayment;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerRef;


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
    private Object paymentType;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billAddr;

    private Boolean applyTaxAfterDiscount;
    private Double homeBalance;
    private Double recurDataRef;
    private Double totalAmt;
    private String taxExemptionRef;
    private Double balance;
    private Double homeTotalAmt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompositeId getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(CompositeId compositeId) {
        this.compositeId = compositeId;
    }
}
