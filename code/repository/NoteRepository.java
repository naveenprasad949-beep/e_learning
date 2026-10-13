package com.learnhub.repository;

import com.learnhub.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
  java.util.List<Note> findByOwnerId(Long ownerId);
}
