package pl.krzysztofbujak.fitstat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {
    List<UsersEntity> findAll();
    UsersEntity findByLogin(String text);
    String findByPassword(String text);
    UsersEntity findIdByLogin(String text);
    List<UsersEntity> findById(int text);
    UsersEntity findLoginById(int id);
    UsersEntity findGrowthById(int id);
}
