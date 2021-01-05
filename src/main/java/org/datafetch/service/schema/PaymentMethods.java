package org.datafetch.service.schema;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "paymentmethod")
public class PaymentMethods extends BaseEntity {

    @Id
    private Integer id;

    @ManyToOne
    private User user;

    private String name;
    private String syncToken;
    private Boolean active;
    private String type;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Object metaDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
