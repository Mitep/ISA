package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Long> {

	Segment findSegmentById(Long id);
}
