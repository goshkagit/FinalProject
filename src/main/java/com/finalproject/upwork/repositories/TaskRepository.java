package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    List<TaskModel> findAllByWhoPosted(UserProfileModel userProfileModel);

    List<TaskModel> findAllByType(Type type);

    List<TaskModel> findAllByTypeAndHardness(Type type, Hardness hardness);

    List<TaskModel> findAllByTypeAndPayment(Type type, int price);

    List<TaskModel> findAllByTypeAndDeadline(Type type, LocalDate deadline);

    List<TaskModel> findAllByHardness(Hardness hardness);

    List<TaskModel> findAllByHardnessAndDeadline(Hardness hardness, LocalDate deadline);

    List<TaskModel> findAllByHardnessAndPayment(Hardness hardness, int price);

    List<TaskModel> findAllByPayment(int price);

    List<TaskModel> findAllByPaymentAndDeadline(int price, LocalDate deadline);

    List<TaskModel> findAllByDeadline(LocalDate deadline);

    List<TaskModel> findAllByTypeAndPaymentAndHardness(Type type, int price, Hardness hardness);

    List<TaskModel> findAllByTypeAndPaymentAndDeadline(Type type, int price, LocalDate deadline);

    List<TaskModel> findAllByPaymentAndDeadlineAndHardness(int price, LocalDate deadline, Hardness hardness);

    List<TaskModel> findAllByTypeAndDeadlineAndHardness(Type type, LocalDate deadline, Hardness hardness);

    List<TaskModel> findAllByTypeAndPaymentAndHardnessAndDeadline(Type type, int price, Hardness hardness, LocalDate deadline);


}
