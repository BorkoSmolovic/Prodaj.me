package com.bbtutorials.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Ads {
	
	@Id
	@Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull(message="Name can't be null")
    private String name;
    
    @Column
    @NotNull(message="Description can't be null")
    private String description;
    
    @Column
    @NotNull(message="Category can't be null")
    private long categoryId;
    
    @Column
    @NotNull(message="City can't be null")
    private long cityId;
    
    @Column
    @NotNull(message="Owner can't be null")
    private long ownerId;
    
    @ManyToOne()
    @JoinColumn(name="ownerId", referencedColumnName = "id", insertable = false, updatable = false)    
    private Users user;

    @Column
    @NotNull(message="Status can't be null")
    private long statusId;
    
    @Column
    @NotNull(message="Type can't be null")
    private long typeId;
    
    @Column
    @NotNull(message="Type can't be null")
    private double price;
}