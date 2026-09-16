package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository;


import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SprintRepository
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository
 *
 * @author Stefan Kiers
 * @since 11-9-2026
 */

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
  List<Sprint> findByProjectId(Long projectId);
}
