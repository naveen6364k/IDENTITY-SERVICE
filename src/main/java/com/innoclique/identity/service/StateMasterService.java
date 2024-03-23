package com.innoclique.identity.service;

import com.innoclique.identity.DTO.StateMaster;
import com.innoclique.identity.entity.StateMasterEntity;
import com.innoclique.identity.repository.StateMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateMasterService {

    @Autowired
    StateMasterRepository stateMasterRepository;

    public List<StateMaster>getAllStates(){
        return toDTOList( stateMasterRepository.findAll());
    }

    private StateMaster toDTO(StateMasterEntity original) {
        StateMaster dto = new StateMaster();
        BeanUtils.copyProperties(original, dto);
        return dto;
    }

    public List<StateMaster> toDTOList(List<StateMasterEntity> originalList) {
        List<StateMaster> dtoList = new ArrayList<>();
        for (StateMasterEntity items : originalList) {
            dtoList.add(toDTO(items));
        }
        return dtoList;
    }
}
