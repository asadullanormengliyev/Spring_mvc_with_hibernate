package pdp.uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.dao.AuthorDao;
import pdp.uz.entity.AuthorEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorDao.save(authorEntity);
    }

    public AuthorEntity getAuthor(Integer id) {
        return authorDao.get(id);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorDao.findAll();
    }

    public AuthorEntity update(AuthorEntity authorEntity) {
        return authorDao.update(authorEntity);
    }

    public void delete(AuthorEntity authorEntity) {
        authorDao.delete(authorEntity);
    }

    public AuthorEntity findById(int id) {
        return authorDao.findById(id);
    }
}
