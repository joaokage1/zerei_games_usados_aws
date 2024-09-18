package com.io.zerei_games_usados_aws.repository.eventlog;


import com.io.zerei_games_usados_aws.model.GameEventKey;
import com.io.zerei_games_usados_aws.model.GameStockEventLog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface GameEventLogRepository extends CrudRepository<GameStockEventLog, GameEventKey> {

    //Nao olha todos os items da tabela por ser parte do partition key
    List<GameStockEventLog> findAllByPk(String code);

    List<GameStockEventLog> findAll();
}
