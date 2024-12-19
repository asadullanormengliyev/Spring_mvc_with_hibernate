package pdp.uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.dao.AuthorDao;
import pdp.uz.dao.BookDao;
import pdp.uz.dto.AuthorDto;
import pdp.uz.dto.BookDto;
import pdp.uz.entity.AuthorEntity;
import pdp.uz.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookEntity save(Integer authorId, BookEntity bookEntity) {
        AuthorEntity authorEntity = authorDao.get(authorId);
        if (authorEntity == null) {
            throw new IllegalArgumentException("Author not found");
        }
        bookEntity.setAuthor(authorEntity);
        return bookDao.save(bookEntity);
    }

    public BookEntity get(Integer id) {
        return bookDao.get(id);
    }

    public AuthorDto findAllBooksByAuthor(Integer authorId) {
        List<BookEntity> bookEntityList = bookDao.findAll(authorId);
        List<BookDto> bookDtoList = new ArrayList<>();

        if (bookEntityList.isEmpty()) {
            // Agar kitoblar ro'yxati bo'sh bo'lsa, AuthorDto ni bo'sh qaytarish
            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(authorId);
            authorDto.setName("Unknown");
            authorDto.setAge(0);
            authorDto.setBooks(bookDtoList);
            return authorDto;
        }

        for (BookEntity bookEntity : bookEntityList) {
            BookDto bookDto = new BookDto();
            bookDto.setId(bookEntity.getId());
            bookDto.setTitle(bookEntity.getTitle());
            bookDtoList.add(bookDto);
        }
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        authorDto.setName(bookEntityList.get(0).getAuthor().getName());
        authorDto.setAge(bookEntityList.get(0).getAuthor().getAge());
        authorDto.setBooks(bookDtoList);
        return authorDto;
    }

    public List<BookEntity> booksByAuthor() {
        return bookDao.bookEntityList();
    }

    public void delete(BookEntity bookEntity) {
        bookDao.delete(bookEntity);
    }

    public List<BookEntity> booksByAuthor(Integer authorId) {
        List<BookEntity> bookEntityList = bookDao.bookEntityList();
        return bookEntityList;
    }


}
