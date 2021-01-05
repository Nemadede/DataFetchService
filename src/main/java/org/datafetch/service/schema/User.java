package org.datafetch.service.schema;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="users")
public class User{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;


    @Column(name = "name")
    private String name;

    @Column(name = "token" ,columnDefinition="TEXT")
    private String token;

    @Column(name = "realm_id")
    private String realmId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "connect_date")
    private LocalDateTime connectDate;

    @Column(name = "code")
    private String code;

    @Column(name = "state")
    private String state;

//    @OneToMany(mappedBy = "userId" )
//    private List<Object> objects;

    public User(){

    }

    public User(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getConnectDate() {
        return connectDate;
    }

    public void setConnectDate(LocalDateTime connectDate) {
        this.connectDate = connectDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", realmId='" + realmId + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", connectDate=" + connectDate +
                ", code='" + code + '\'' +
                '}';
    }
}
