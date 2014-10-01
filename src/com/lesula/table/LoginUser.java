package com.lesula.table;


import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LoginUser implements FieldHandled{
	
    private FieldHandler fieldHandler;

	private int id;
	private String email;
	private String password;
    private boolean isActive;
    private UserAddress userAddress;

    public LoginUser() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Id")
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Column(name="Email", length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Column(name="Password", length=100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column(name = "IsActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL, mappedBy = "user")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public UserAddress getUserAddress() {
        if (fieldHandler != null) {
            return (UserAddress) fieldHandler.readObject(this, "userAddress", userAddress);
        }
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public void setFieldHandler(FieldHandler handler) {
        this.fieldHandler = fieldHandler;
    }

    @Override
    @Transient
    public FieldHandler getFieldHandler() {
        return fieldHandler;
    }
}
