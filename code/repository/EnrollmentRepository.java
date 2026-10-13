package com.learnhub.repository;

import com.learnhub.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  java.util.List<Enrollment> findByOwnerId(Long ownerId);

  boolean existsByOwnerIdAndCourseId(Long ownerId, Long courseId);
}
