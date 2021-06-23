package org.datafetch.service.schema;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="customer")
public class Customers extends BaseEntity implements Serializable {

    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @EmbeddedId
    private CompositeId compositeId;

    @MapsId(value = "user_id")
    @ManyToOne
    private User user;

    private String syncToken;
    private String displayName;
    private String title;
    private String givenName;
    private String middleName;
    private String suffix;
    private String familyName;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object primaryEmailAddr;
    private String resaleNum;
    private String secondaryTaxIdentifier;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object arAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object defaultTaxCodeRef;

    private String preferredDeliveryMethod;
    private String gstin;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object salesTermRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object customerTypeRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object fax;

    private String businessNumber;
    private Boolean billWithParent;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object mobile;

    private Boolean job;
    private Double balanceWithJobs;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object primaryPhone;

    private Date openBalanceDate;
    private Boolean taxable;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object alternatePhone;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object parentRef;

    @Column(columnDefinition="TEXT")
    private String notes;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object webAddr;

    private Boolean active;
    private Double balance;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object shipAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object paymentMethodRef;

    private Boolean isProject;
    private  String CompanyName;
    private String primaryTaxIdentifier;
    private String gstRegistrationType;

    private String printOnCheckName;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billAddr;

    private String fullQualifiedName;
    private Integer level;
    private Integer TaxExemptionReasonId;


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
