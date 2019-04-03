package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.services.TaskFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskFilterServiceImpl implements TaskFilterService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<TaskModel> whereTypeIs(String type) {
        return taskRepository.findAllByType(Type.valueOf(type.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereTypeAndHardnessIs(String type, String hardness) {
        return taskRepository.findAllByTypeAndHardness(Type.valueOf(type.toUpperCase()), Hardness.valueOf(hardness.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereTypeAndPaymentIs(String type, int price) {
        return taskRepository.findAllByTypeAndPayment(Type.valueOf(type.toUpperCase()), price);
    }

    @Override
    public List<TaskModel> whereTypeAndDeadlineIs(String type, LocalDate deadline) {
        return taskRepository.findAllByTypeAndDeadline(Type.valueOf(type.toUpperCase()), deadline);
    }

    @Override
    public List<TaskModel> whereHardnessIs(String hardness) {
        return taskRepository.findAllByHardness(Hardness.valueOf(hardness.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereHardnessAndDeadlineIs(String hardness, LocalDate deadline) {
        return taskRepository.findAllByHardnessAndDeadline(Hardness.valueOf(hardness.toUpperCase()), deadline);
    }

    @Override
    public List<TaskModel> whereHardnessAndPaymentIs(String hardness, int price) {
        return taskRepository.findAllByHardnessAndPayment(Hardness.valueOf(hardness.toUpperCase()), price);
    }

    @Override
    public List<TaskModel> wherePaymentIs(int price) {
        return taskRepository.findAllByPayment(price);
    }

    @Override
    public List<TaskModel> wherePaymentAndDeadlineIs(int price, LocalDate deadline) {
        return taskRepository.findAllByPaymentAndDeadline(price, deadline);
    }

    @Override
    public List<TaskModel> whereDeadlineIs(LocalDate deadline) {
        return taskRepository.findAllByDeadline(deadline);
    }

    @Override
    public List<TaskModel> whereTypeAndPaymentAndHardnessIs(String type, int price, String hardness) {
        return taskRepository.findAllByTypeAndPaymentAndHardness(Type.valueOf(type.toUpperCase()), price, Hardness.valueOf(hardness.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereTypeAndPaymentAndDeadlineIs(String type, int price, LocalDate deadline) {
        return taskRepository.findAllByTypeAndPaymentAndDeadline(Type.valueOf(type.toUpperCase()), price, deadline);
    }

    @Override
    public List<TaskModel> wherePaymentAndDeadlineAndHardnessIs(int price, LocalDate deadline, String hardness) {
        return taskRepository.findAllByPaymentAndDeadlineAndHardness(price, deadline, Hardness.valueOf(hardness.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereTypeAndDeadlineAndHardnessIs(String type, LocalDate deadline, String hardness) {
        return taskRepository.findAllByTypeAndDeadlineAndHardness(Type.valueOf(type.toUpperCase()), deadline, Hardness.valueOf(hardness.toUpperCase()));
    }

    @Override
    public List<TaskModel> whereTypeAndPaymentAndHardnessAndDeadlineIs(String type, int price, String hardness, LocalDate deadline) {
        return taskRepository.findAllByTypeAndPaymentAndHardnessAndDeadline(Type.valueOf(type.toUpperCase()), price, Hardness.valueOf(hardness.toUpperCase()), deadline);

    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll();

    }
}
