package de.amehlen.booklibrary.repository;

import de.amehlen.booklibrary.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findBookByIsbn(String isbn);

}
