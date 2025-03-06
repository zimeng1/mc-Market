package org.ethh.marketMakerManager.service;

import org.ethh.dal.entity.AlgorithmParameters;
import org.ethh.dal.mapper.AlgorithmParametersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wangyifei
 */

@Service
public class AlgorithmParameterService {

    @Autowired
    private AlgorithmParametersMapper algorithmParametersMapper;

    public AlgorithmParameters getById(Long id) {
        return algorithmParametersMapper.getById(id);
    }

    public List<AlgorithmParameters> getAllWithPagination(int limit, int offset) {
        return algorithmParametersMapper.getAllWithPagination(limit, offset);
    }

    public Long insert(AlgorithmParameters algorithmParameters) {
        return algorithmParametersMapper.insert(algorithmParameters);
    }

    public void update(AlgorithmParameters algorithmParameters) {
        algorithmParametersMapper.update(algorithmParameters);
    }

    public void delete(Long id) {
        algorithmParametersMapper.delete(id);
    }

}
