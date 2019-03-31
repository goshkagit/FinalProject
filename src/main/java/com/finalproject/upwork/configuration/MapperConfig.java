package com.finalproject.upwork.configuration;

import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserProfileModel;
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
    public ModelMapper taskModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(taskModelConverter);
        return modelMapper;
    }

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean
    public ModelMapper profileModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(profileModelConverter);
        return modelMapper;
    }

  private  Converter<TaskDTO , TaskModel> taskModelConverter = new Converter<TaskDTO, TaskModel>() {

        @Override
        public TaskModel convert(MappingContext<TaskDTO, TaskModel> mappingContext) {

            TaskDTO dto = mappingContext.getSource();

            TaskModel model = new TaskModel();

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

  private   Converter<UserProfileModelDTO, UserProfileModel> profileModelConverter = new Converter<UserProfileModelDTO, UserProfileModel>() {
        @Override
        public UserProfileModel convert(MappingContext<UserProfileModelDTO, UserProfileModel> mappingContext) {

            UserProfileModelDTO dto  = mappingContext.getSource();

            UserProfileModel model = new UserProfileModel();

            model.setName(dto.getName());
            model.setSurname(dto.getSurname());
            model.setEmail(dto.getEmail());
            model.setPortfolio(dto.getPortfolio());
            model.setId(dto.getId());
            model.setSkill(Type.valueOf(dto.getSkill().toUpperCase()));
            return model;

        }
    };



}
