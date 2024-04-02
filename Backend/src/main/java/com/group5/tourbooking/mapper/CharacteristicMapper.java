package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.CharacteristicDto;
import com.group5.tourbooking.model.Characteristic;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public class CharacteristicMapper {
    public CharacteristicDto characteristicToDto(Characteristic characteristic){
        if(characteristic == null){
            return null;
        }
        CharacteristicDto dto= new CharacteristicDto();

        dto.setId(characteristic.getId());
        dto.setName(characteristic.getName());
        dto.setUrlCharacteristicImage(characteristic.getUrlCharacteristicImage());

        return dto;
    }

    public Characteristic dtoToCharacteristic(CharacteristicDto characteristicDto){
        if(characteristicDto == null){
            return null;
        }
        Characteristic characteristic= new Characteristic();

        characteristic.setId(characteristicDto.getId());
        characteristic.setName(characteristicDto.getName());
        characteristic.setUrlCharacteristicImage(characteristicDto.getUrlCharacteristicImage());

        return characteristic;
    }
}
