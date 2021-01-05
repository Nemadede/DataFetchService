package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "payment")
public class Payments extends BaseEntity{

    @Id
    private Integer id;

    @ManyToOne
    private User user;

    private Double totalAmt;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerRef;

    private String syncToken;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    private String privateNote;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object paymentMethodRef;

    private Double unappliedAmt;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object depositToAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object exchangeRate;

    private String txnSource;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object line;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object aRAccountRef;

    private Date txnDate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object creditCardPayment;

    private String transactionLocationType;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private String paymentRefNum;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object taxExemptionRef;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
