package isa.projekat.service;

import isa.projekat.model.dtos.SegmentDTO;

public interface SegmentService {

	void addNewSegment(SegmentDTO segment);
	
	void editSegment(SegmentDTO segment);

	void deleteSegment(SegmentDTO segment);
	
}
