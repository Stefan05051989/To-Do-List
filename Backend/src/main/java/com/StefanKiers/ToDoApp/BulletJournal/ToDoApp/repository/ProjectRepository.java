package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository;


import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ProjectRepository
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 **/

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
