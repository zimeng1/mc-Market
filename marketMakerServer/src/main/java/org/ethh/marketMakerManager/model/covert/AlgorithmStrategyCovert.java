package org.ethh.marketMakerManager.model.covert;


import org.ethh.dal.entity.AlgorithmStrategy;
import org.ethh.marketMakerManager.model.AlgorithmStrategyDTO;
import org.ethh.marketMakerManager.model.covert.conversionWorker.TypeConversionWorker;
import org.mapstruct.Mapper;

/**
 * @author wangyifei
 */
@Mapper(componentModel = "spring", uses = {TypeConversionWorker.class})
public interface AlgorithmStrategyCovert {

    AlgorithmStrategyDTO toDTO(AlgorithmStrategy entity);

    AlgorithmStrategy toDO(AlgorithmStrategyDTO dto);
}
