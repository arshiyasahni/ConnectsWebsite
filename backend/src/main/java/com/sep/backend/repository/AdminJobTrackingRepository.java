package com.sep.backend.repository;

import com.sep.backend.models.AdminTrackJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Admin Track Job repository.
 */
public interface AdminJobTrackingRepository extends JpaRepository<AdminTrackJob, Integer> {

    List<AdminTrackJob> findAll();


}
