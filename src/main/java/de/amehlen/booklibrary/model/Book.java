package de.amehlen.booklibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

  @NotEmpty
  @Length(min = 9, max = 13, message = "ISBN must be a valid ISBN-10 or ISBN-13")
  @Column(name = "book_isbn", length = 13, unique = true)
  private String isbn;

  @NotEmpty
  @Length(max = 150)
  @Column(name = "book_title", length = 150)
  private String title;

  @NotEmpty
  @Length(max = 150)
  @Column(name = "book_author", length = 150)
  private String author;

  @NotEmpty
  @Length(max = 150)
  @Column(name = "book_publisher", length = 150)
  private String publisher;

  @Column(name = "book_publication_year")
  private short publicationYear;

  @Min(1)
  @Column(name = "book_edition")
  private short edition;

  @NotEmpty
  @Length(max = 300)
  @Column(name = "book_description", length = 300)
  private String description;

  @Min(0)
  @Column(name = "book_pages")
  private Integer pages;
}
