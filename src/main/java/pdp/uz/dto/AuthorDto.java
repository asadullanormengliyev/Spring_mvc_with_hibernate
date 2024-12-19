package pdp.uz.dto;

import lombok.*;
import pdp.uz.entity.BookEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AuthorDto {
    private int id;
    private String name;
    private int age;
    List<BookDto> books;
}
