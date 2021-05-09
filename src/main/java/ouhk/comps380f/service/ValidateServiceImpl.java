package ouhk.comps380f.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ouhk.comps380f.exception.CustomException;

import java.util.Optional;

@Service
public class ValidateServiceImpl implements ValidateService{
    @Override
    public <T, V> T validateExist(CrudRepository<T, V> repo, V id, String error) {
        Optional<T> t = repo.findById(id);
        if (!t.isPresent()){
            throw new CustomException(error);
        }
        return t.get();
    }
}
