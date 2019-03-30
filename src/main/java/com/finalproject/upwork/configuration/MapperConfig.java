package com.finalproject.upwork.configuration;

import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Type;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(dtoToModel);
        return modelMapper;

    }

    Converter<TaskDTO , TaskModel> dtoToModel = new Converter<TaskDTO, TaskModel>() {

        @Override
        public TaskModel convert(MappingContext<TaskDTO, TaskModel> mappingContext) {

            TaskDTO dto = mappingContext.getSource();

            TaskModel model = mappingContext.getDestination();
            model.setTopic(dto.getTopic());
            model.setDeadline(dto.getDeadline());
            model.setDescription(dto.getDescription());
            model.setPayment(dto.getPayment());
            model.setTaskId(dto.getTaskID());
            model.setType(Type.valueOf(dto.getType().toUpperCase()));
            model.setHardness(Hardness.valueOf(dto.getHardness().toUpperCase()));
            return model;
        }
    };

}
