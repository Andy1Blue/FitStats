package pl.krzysztofbujak.fitstat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysztofbujak.fitstat.entities.DataEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, Integer> {
    List<DataEntity> findAllByOrderByIdDesc();
    List<DataEntity> findByUsers_IdOrderByAddingDateDesc(int text);
    List<DataEntity> findByUsers_IdOrderByIdDesc(int text);
    DataEntity findByUsers_Id(int text);
    List<DataEntity> findByUsers_IdOrderByWeightDesc(int text);
    List<DataEntity> findByUsers_IdOrderByWeightAsc(int text);
    List<DataEntity> countByUsers_IdOrderByIdDesc(int text);
    List<DataEntity> findByUsers_IdOrderByIdAsc(int text);
    List<DataEntity> findWeightByUsers_IdOrderByIdDesc(int text);
    List<DataEntity>findFatByUsers_IdOrderByIdDesc(int text);
    List<DataEntity>findWaterByUsers_IdOrderByIdDesc(int text);
    List<DataEntity>findMusclesByUsers_IdOrderByIdDesc(int text);
    List<DataEntity>findBonesByUsers_IdOrderByIdDesc(int text);
}
