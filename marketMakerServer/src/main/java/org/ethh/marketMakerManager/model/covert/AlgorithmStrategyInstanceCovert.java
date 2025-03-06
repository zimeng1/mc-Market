package org.ethh.marketMakerManager.model.covert;


import org.ethh.dal.entity.AlgorithmStrategyInstance;
import org.ethh.marketMakerManager.model.AlgorithmStrategyInstanceDTO;
import org.ethh.marketMakerManager.model.covert.conversionWorker.TypeConversionWorker;
import org.mapstruct.Mapper;

/**
 * @author wangyifei
 * @description DTO 与do 的转换器
 */
@Mapper(componentModel = "spring", uses = {TypeConversionWorker.class})
public interface AlgorithmStrategyInstanceCovert {

    AlgorithmStrategyInstanceDTO toDTO(AlgorithmStrategyInstance entity);

    AlgorithmStrategyInstance toDO(AlgorithmStrategyInstanceDTO dto);

}
