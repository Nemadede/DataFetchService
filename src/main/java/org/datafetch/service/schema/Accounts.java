package org.datafetch.service.schema;


import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name="account")
public class Accounts extends BaseEntity {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne
    private User user;

    private String name;
    private String syncToken;
    private String acctNum;

    private String description;
    private Boolean active;

    private Boolean subAccount;
    private String classification;
    private String fullyQualifiedName;
    private String txnLocationType;
    private String accountType;
    private Float currentBalanceWithSubAccounts;
    private String accountAlias;
    private String accountSubType;
    private Float currentBalance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object parentRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object taxCodeRef;


    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", syncToken='" + syncToken + '\'' +
                ", acctNum='" + acctNum + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", subAccount=" + subAccount +
                ", classification='" + classification + '\'' +
                ", fullyQualifiedName='" + fullyQualifiedName + '\'' +
                ", txnLocationType='" + txnLocationType + '\'' +
                ", accountType='" + accountType + '\'' +
                ", currentBalanceWithSubAccounts=" + currentBalanceWithSubAccounts +
                ", accountAlias='" + accountAlias + '\'' +
                ", accountSubType='" + accountSubType + '\'' +
                ", currentBalance=" + currentBalance +
                ", currencyRef=" + currencyRef +
                ", parentRef=" + parentRef +
                ", metaData=" + metaData +
                ", taxCodeRef=" + taxCodeRef +
                '}';
    }
}
