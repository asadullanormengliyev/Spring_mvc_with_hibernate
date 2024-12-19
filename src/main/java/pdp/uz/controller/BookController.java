package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.uz.entity.BookEntity;

import pdp.uz.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public String bookList(Model model) {
        List<BookEntity> bookEntityList = bookService.booksByAuthor();
        model.addAttribute("books", bookEntityList);
        return "book/book";
    }

    @PostMapping("/add")
    public String addBook(BookEntity bookEntity, @RequestParam("authorId") Integer authorId) {
        bookService.save(authorId, bookEntity);
        return "redirect:/book/list";
    }

    @PostMapping("/delete")
    public String deleteBook(BookEntity bookEntity) {
        bookService.delete(bookEntity);
        return "redirect:/book/list";
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookEntity getBookById(@PathVariable("id") Integer id) {
        return bookService.get(id);
    }

}