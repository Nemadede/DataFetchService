package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="bill")
public class Bills {

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

    private Date txnDate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object apAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object salesTermRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object linkedTxn;

    private String globalTaxCalculation;
    private Double totalAmt;
    private String transactionLocationType;
    private Date dueDate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private String docNumber;
    private String privateNote;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object txnTaxDetail;

    private Double exchangeRate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object departmentRef;

    private Boolean includeInAnnualTPAR;
    private Double homeBalance;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object recurDataRef;

    private Double balance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", vendorRef=" + vendorRef +
                ", line=" + line +
                ", syncToken='" + syncToken + '\'' +
                ", currencyRef=" + currencyRef +
                ", txnDate=" + txnDate +
                ", apAccountRef=" + apAccountRef +
                ", salesTermRef=" + salesTermRef +
                ", linkedTxn=" + linkedTxn +
                ", globalTaxCalculation='" + globalTaxCalculation + '\'' +
                ", totalAmt=" + totalAmt +
                ", transactionLocationType='" + transactionLocationType + '\'' +
                ", dueDate=" + dueDate +
                ", metaData=" + metaData +
                ", docNumber='" + docNumber + '\'' +
                ", privateNote='" + privateNote + '\'' +
                ", txnTaxDetail=" + txnTaxDetail +
                ", exchangeRate=" + exchangeRate +
                ", departmentRef=" + departmentRef +
                ", includeInAnnualTPAR=" + includeInAnnualTPAR +
                ", homeBalance=" + homeBalance +
                ", recurDataRef=" + recurDataRef +
                ", balance=" + balance +
                '}';
    }
}
