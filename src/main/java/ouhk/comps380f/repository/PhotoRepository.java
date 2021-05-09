package ouhk.comps380f.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ouhk.comps380f.dao.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
