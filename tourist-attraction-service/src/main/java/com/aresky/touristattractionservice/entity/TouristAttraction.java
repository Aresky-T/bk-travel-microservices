package com.aresky.touristattractionservice.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tourist_attraction")
public class TouristAttraction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @OneToMany(mappedBy = "touristAttraction", fetch = FetchType.LAZY)
    private List<Blog> blogs;

    public TouristAttraction(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public TouristAttraction(String name, String imageUrl, String introduction) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
    }
}
