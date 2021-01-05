package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="billpayment")
public class BillPayments extends BaseEntity{

    @Id
    @Column(name="id", nullable = false, unique = false)
    private Integer id;

    @ManyToOne
    private User user;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object vendorRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object line;

    private Double totalAmt;
    private String payType;
    private String syncToken;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    private String docNumber;
    private String privateNote;
    private Date txnDate;
    private Double exchangeRate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object apAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object departmentRef;

    private String transactionLocationType;
    private Boolean processBillPayment;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object checkPayment;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object creditCardPayment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BillPayment{" +
                "Id=" + id +
                ", vendorRef=" + vendorRef +
                ", line=" + line +
                ", totalAmt=" + totalAmt +
                ", payType='" + payType + '\'' +
                ", syncToken='" + syncToken + '\'' +
                ", currencyRef=" + currencyRef +
                ", docNumber='" + docNumber + '\'' +
                ", privateNote='" + privateNote + '\'' +
                ", txnDate=" + txnDate +
                ", exchangeRate=" + exchangeRate +
                ", apAccountRef=" + apAccountRef +
                ", departmentRef=" + departmentRef +
                ", transactionLocationType='" + transactionLocationType + '\'' +
                ", processBillPayment=" + processBillPayment +
                ", metaData=" + metaData +
                ", checkPayment='" + checkPayment + '\'' +
                ", creditCardPayment='" + creditCardPayment + '\'' +
                '}';
    }

}
