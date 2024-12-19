package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.uz.dto.AuthorDto;
import pdp.uz.entity.AuthorEntity;
import pdp.uz.entity.BookEntity;
import pdp.uz.service.AuthorService;
import pdp.uz.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/list")
    public String authorList(Model model) {
        List<AuthorEntity> allAuthors = authorService.getAllAuthors();
        model.addAttribute("authors", allAuthors);
        return "author/authorList";
    }

    @PostMapping("/add")
    public String addAuthor(AuthorEntity author, Model model) {
        authorService.save(author);
        List<AuthorEntity> allAuthors = authorService.getAllAuthors();
        model.addAttribute("authors", allAuthors);
        return "author/authorList";
    }

    @PostMapping("/delete")
    public String deleteAuthor(AuthorEntity author) {
        System.out.println(author + "author");
        authorService.delete(author);
        return "redirect:/author/list";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuthorEntity getAuthor(@PathVariable("id") Integer id) {
        return authorService.getAuthor(id);
    }

    @GetMapping("/bookList")
    public String bookList(@RequestParam("authorId") Integer authorId, Model model) {
        AuthorDto allBooksByAuthor = bookService.findAllBooksByAuthor(authorId);
        model.addAttribute("books", allBooksByAuthor.getBooks());
        return "author/author.html";
    }

}