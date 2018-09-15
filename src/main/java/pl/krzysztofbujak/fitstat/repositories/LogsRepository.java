package pl.krzysztofbujak.fitstat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofbujak.fitstat.entities.LogsEntity;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;

import java.util.List;

@Repository
public interface LogsRepository extends CrudRepository<LogsEntity, Integer> {
    List<LogsEntity> findByUsers_IdOrderByIdDesc(int id);
}
