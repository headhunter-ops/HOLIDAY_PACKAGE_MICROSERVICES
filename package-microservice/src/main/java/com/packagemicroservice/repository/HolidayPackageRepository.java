package com.packagemicroservice.repository;

import com.packagemicroservice.entity.HolidayPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayPackageRepository extends JpaRepository<HolidayPackage,Long> {
}
