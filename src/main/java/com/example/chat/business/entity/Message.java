package com.example.chat.business.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String body;

    private Timestamp updatedAt;

    @Column(updatable = false)
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp((new Date()).getTime());
        this.createdAt = ts;
        this.updatedAt = ts;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp((new Date()).getTime());
    }
}
