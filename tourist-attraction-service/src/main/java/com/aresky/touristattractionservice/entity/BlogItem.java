package com.aresky.touristattractionservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "blog_item")
public class BlogItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sub_title", nullable = false)
    private String subTitle;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id", nullable = false)
    private Blog blog;

    public BlogItem(String subTitle, String imageUrl, String content) {
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public BlogItem(Integer id, String subTitle, String imageUrl, String content) {
        this.id = id;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog item: [ id = " + this.id
                + ", subTitle = " + this.subTitle
                + ", imageUrl = " + this.imageUrl
                + ", content = " + this.content
                + " ]\n";
    }
}
