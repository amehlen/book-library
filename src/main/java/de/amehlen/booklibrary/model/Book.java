package de.amehlen.booklibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private Long id;

  @Column(name = "book_isbn")
  private String isbn;

  @Column(name = "book_title")
  private String title;

  @Column(name = "book_author")
  private String author;

  @Column(name = "book_publisher")
  private String publisher;

  @Column(name = "book_publication_year")
  private short publicationYear;

  @Column(name = "book_description")
  private String description;

  @Column(name = "book_pages")
  private Integer pages;
}
