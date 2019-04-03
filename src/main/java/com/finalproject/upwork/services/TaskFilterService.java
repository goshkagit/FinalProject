package com.finalproject.upwork.services;

import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Type;

import java.time.LocalDate;
import java.util.List;

public interface TaskFilterService {

    List<TaskModel> whereTypeIs(String type);

    List<TaskModel> whereTypeAndHardnessIs(String type, String hardness);

    List<TaskModel> whereTypeAndPaymentIs(String type, int price);

    List<TaskModel> whereTypeAndDeadlineIs(String type, LocalDate deadline);

    List<TaskModel> whereHardnessIs(String hardness);

    List<TaskModel> whereHardnessAndDeadlineIs(String hardness, LocalDate deadline);

    List<TaskModel> whereHardnessAndPaymentIs(String hardness, int price);

    List<TaskModel> wherePaymentIs(int price);

    List<TaskModel> wherePaymentAndDeadlineIs(int price, LocalDate deadline);

    List<TaskModel> whereDeadlineIs(LocalDate deadline);

    List<TaskModel> whereTypeAndPaymentAndHardnessIs(String type, int price, String hardness);

    List<TaskModel> whereTypeAndPaymentAndDeadlineIs(String type, int price, LocalDate deadline);

    List<TaskModel> wherePaymentAndDeadlineAndHardnessIs(int price, LocalDate deadline, String hardness);

    List<TaskModel> whereTypeAndDeadlineAndHardnessIs(String type, LocalDate deadline, String hardness);

    List<TaskModel> whereTypeAndPaymentAndHardnessAndDeadlineIs(String type, int price, String hardness, LocalDate deadline);

    List<TaskModel> findAll();
}
