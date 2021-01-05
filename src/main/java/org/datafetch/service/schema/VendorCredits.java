package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="vendorcredit")
public class VendorCredits extends BaseEntity{

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne
    private User user;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object vendorRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object line;

    private String syncToken;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    private String docNumber;
    private String privateNote;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object linkedTxn;

    private String globalTaxCalculation;
    private String exchangeRate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object aPAccountRef;

    private Date txnDate;
    private Boolean includeInAnnualTPAR;
    private String transactionLocationType;
    private Double balance;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object recurDataRef;

    private Double totalAmt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
