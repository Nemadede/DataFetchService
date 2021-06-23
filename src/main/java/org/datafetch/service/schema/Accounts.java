package org.datafetch.service.schema;


import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name="account")
public class Accounts extends BaseEntity {

    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @EmbeddedId
    private CompositeId compositeId;

    @MapsId(value = "user_id")
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public CompositeId getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(CompositeId compositeId) {
        this.compositeId = compositeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
