package org.ethh.marketMakerManager.model.covert;

import org.ethh.marketMakerManager.model.AlgorithmParametersDTO;
import org.ethh.marketMakerManager.model.covert.conversionWorker.TypeConversionWorker;
import org.mapstruct.Mapper;
import org.ethh.dal.entity.AlgorithmParameters;
import org.mapstruct.Mapping;


/**
 * @author wangyifei
 * @description DTO 与do 的转换器
 */
@Mapper(componentModel = "spring", uses = {TypeConversionWorker.class})
public interface AlgorithmParametersCovert {


    @Mapping(source="algorithmArgs", target="algorithmArgs", qualifiedByName="toJsonObject")
    AlgorithmParametersDTO toDTO(AlgorithmParameters entity);

    @Mapping(source="algorithmArgs", target="algorithmArgs", qualifiedByName="toJsonString")
    AlgorithmParameters toDO(AlgorithmParametersDTO dto);

}
