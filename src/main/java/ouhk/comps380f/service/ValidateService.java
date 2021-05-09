package ouhk.comps380f.service;

import org.springframework.data.repository.CrudRepository;

public interface ValidateService {

    <T,V> T validateExist(CrudRepository<T, V> repo, V id, String error);


}
