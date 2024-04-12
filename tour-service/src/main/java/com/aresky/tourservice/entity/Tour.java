package com.aresky.tourservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tour")
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "destinations", nullable = false)
    private String destinations;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "vehicle", nullable = false)
    private String vehicle;

    @Column(name = "schedules", nullable = false)
    private String schedules;

    @Column(name = "adult_price", nullable = false)
    private Integer adultPrice;

    @Column(name = "children_price", nullable = false)
    private Integer childrenPrice;

    @Column(name = "baby_price", nullable = false)
    private Integer babyPrice;

    @Column(name = "image1", nullable = false)
    private String image1;

    @Column(name = "image2", nullable = false)
    private String image2;

    @Column(name = "image3", nullable = false)
    private String image3;

    @Column(name = "image4", nullable = false)
    private String image4;

    @Column(name = "total_sub_tours", nullable = false)
    private Integer totalSubTours;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private List<SubTour> subTours;
}
