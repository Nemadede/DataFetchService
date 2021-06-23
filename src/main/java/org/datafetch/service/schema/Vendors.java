package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "vendor")
public class Vendors extends BaseEntity {

    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @EmbeddedId
    private CompositeId compositeId;

    @MapsId(value = "user_id")
    @ManyToOne
    private User user;

    private String syncToken;
    private String title;
    private String givenName;
    private String middleName;
    private String suffix;
    private String familyName;
    private String displayName;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object primaryEmailAddr;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object otherContactInfo;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object aPAccountRef;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object termRef;

    private String GSTIN;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object fax;

    private String businessNumber;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object currencyRef;

    private Boolean hasTPAR;
    private String taxReportingBasis;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object mobile;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object primaryPhone;

    private Boolean alternatePhone;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaData;

    private Boolean vendor1099;
    private Double billRate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object webAddr;

    private String companyName;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object vendorPaymentBankDetail;

    private String taxIdentifier;
    private String acctNum;
    private String gSTRegistrationType;
    private String printOnCheckName;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object billAddr;

    private Double balance;

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
