
package com.finalproject.upwork.controllers;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.services.TaskFilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Secured(value = "ROLE_USER")
public class TaskFilterController {

    @Autowired
    private TaskFilterService taskFilterService;

    @Autowired
    private ModelMapper taskDTOModelMapper;


    @GetMapping("/typeIs/{type}")
    public ResponseEntity getAllByType(@PathVariable String type) {
        List<TaskModel> all = taskFilterService.whereTypeIs(type);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with type : " + type);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);

    }

    @GetMapping("/typeAndHardnessIs/{type}/{hardness}")
    public ResponseEntity getAllByTypeAndHardness(@PathVariable String type, @PathVariable String hardness) {
        List<TaskModel> all = taskFilterService.whereTypeAndHardnessIs(type, hardness);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with type :" + type + " and hardness: " + hardness);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/typeAndPaymentIs/{type}/{payment}")
    public ResponseEntity getAllByTypeAndPayment(@PathVariable String type, @PathVariable int payment) {
        List<TaskModel> all = taskFilterService.whereTypeAndPaymentIs(type, payment);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with type :" + type + " and payment: " + payment);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/typeAndDeadlineIs/{type}/{deadline}")
    public ResponseEntity getAllByTypeAndDeadline(@PathVariable String type, @PathVariable LocalDate deadline) {
        List<TaskModel> all = taskFilterService.whereTypeAndDeadlineIs(type, deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with type :" + type + " and deadline: " + deadline);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/hardnessIs/{hardness}")
    public ResponseEntity getAllByHardness(@PathVariable String hardness) {
        List<TaskModel> all = taskFilterService.whereHardnessIs(hardness);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with hardness :" + hardness);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/hardnessAndDeadlineIs/{hardness}/{deadline}")
    public ResponseEntity getAllByHardnessAndDeadline(@PathVariable String hardness, @PathVariable LocalDate deadline) {
        List<TaskModel> all = taskFilterService.whereHardnessAndDeadlineIs(hardness, deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with hardness :" + hardness + " and deadline: " + deadline);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/hardnessAndPaymentIs/{hardness}/{payment}")
    public ResponseEntity getAllByHardnessAndPayment(@PathVariable String hardness, @PathVariable int payment) {
        List<TaskModel> all = taskFilterService.whereTypeAndPaymentIs(hardness, payment);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with hardness :" + hardness + " and payment: " + payment);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/paymentIs/{payment}")
    public ResponseEntity getAllByPayment(@PathVariable int payment) {
        List<TaskModel> all = taskFilterService.wherePaymentIs(payment);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with payment :" + payment);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/paymentAndDeadlineIs/{payment}/{deadline}")
    public ResponseEntity getAllByPaymentAndDeadline(@PathVariable int payment, @PathVariable LocalDate deadline) {
        List<TaskModel> all = taskFilterService.wherePaymentAndDeadlineIs(payment, deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with payment :" + payment + " and deadline: " + deadline);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/deadlineIs/{deadline}")
    public ResponseEntity getAllByDeadline(@PathVariable LocalDate deadline) {
        List<TaskModel> all = taskFilterService.whereDeadlineIs(deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with deadline :" + deadline);
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/typeAndPaymentAndHardnessIs/{type}/{payment}/{hardness}")
    public ResponseEntity getAllByTypeAndPaymentAndHardness(@PathVariable String type, @PathVariable int payment, @PathVariable String hardness) {
        List<TaskModel> all = taskFilterService.whereTypeAndPaymentAndHardnessIs(type, payment, hardness);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with all those parameters");
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/typeAndPaymentAndDeadlineIs/{type}/{payment}/{deadline}")
    public ResponseEntity getAllByTypeAndPaymentAndDeadline(@PathVariable String type, @PathVariable int payment, @PathVariable LocalDate deadline) {
        List<TaskModel> all = taskFilterService.whereTypeAndPaymentAndDeadlineIs(type, payment, deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with all those parameters");
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/typeAndDeadlineAndHardnessIs/{type}/{deadline}/{hardness}")
    public ResponseEntity getAllTypeAndDeadlineAndHardness(@PathVariable String type, @PathVariable LocalDate deadline, @PathVariable String hardness) {
        List<TaskModel> all = taskFilterService.whereTypeAndDeadlineAndHardnessIs(type, deadline, hardness);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with all those parameters");
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

    @GetMapping("/allFilters/{type}/{payment}/{deadline}/{hardness}")
    public ResponseEntity getAllFilters(@PathVariable String type, @PathVariable LocalDate deadline, @PathVariable int payment, @PathVariable String hardness) {
        List<TaskModel> all = taskFilterService.whereTypeAndPaymentAndHardnessAndDeadlineIs(type, payment, hardness, deadline);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks with all those parameters");
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }


    @GetMapping("/allTasks")
    public ResponseEntity getAll() {
        List<TaskModel> all = taskFilterService.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("There is no tasks available");
        }
        List<TaskDTO> allDTO = all
                .stream()
                .map(TaskModel -> taskDTOModelMapper.map(TaskModel, TaskDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }


}
