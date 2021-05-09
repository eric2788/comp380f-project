package ouhk.comps380f.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ouhk.comps380f.dao.Cart;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    List<Cart> findByAccount_Username(String username);

}
