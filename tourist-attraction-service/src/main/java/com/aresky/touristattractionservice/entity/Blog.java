package com.aresky.touristattractionservice.entity;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "intro", nullable = false)
    private String intro;

    @Column(name = "author", nullable = false)
    private String author;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @ManyToOne
    @JoinColumn(name = "tourist_attraction_id", referencedColumnName = "id", nullable = false)
    private TouristAttraction touristAttraction;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<BlogItem> items;

    public Blog(String title, String imageUrl, String intro) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.intro = intro;
        this.author = "BK TRAVEL";
    }

    public Blog(String title, String imageUrl, String intro, String author) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.intro = intro;
        this.author = author;
    }
}
