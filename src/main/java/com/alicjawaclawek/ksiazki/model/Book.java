package com.alicjawaclawek.ksiazki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//nie uzywamy polecen Column i nie nadajemy nazwy, bo nie mamy juz gotowej bazy,
//wiec SQL moze sobie nadawac nazwy dla kolumn takie jak bedzoe chcial na podstawie kodu

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY, zeby nie laczyl generacji z druga klasa
    private Integer id;
    private String title;

    @JsonProperty("autor")
    private String author;

    @JsonIgnore
    private String isbn;

    //@JsonIgnore
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", updatable = false, insertable = false)
    private Category category;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoryId=" + categoryId +
                ", category=" + category.getCategoryName() +
                '}';
    }
}
