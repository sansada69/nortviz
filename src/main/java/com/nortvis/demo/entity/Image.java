package com.nortvis.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    @Id
    private String id;
    private String link;
    @JsonIgnore
    private String deleteHash;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
