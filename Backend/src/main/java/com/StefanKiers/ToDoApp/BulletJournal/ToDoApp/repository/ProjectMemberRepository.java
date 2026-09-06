package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * ProjectMemberRepository
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 **/

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
  List<ProjectMember> findByProjectId(Integer projectId);
  Optional<ProjectMember> findByProjectIdAndUserId(Integer projectId, Long userId);
}
