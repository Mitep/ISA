package isa.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.Hall;
import isa.projekat.model.Segment;
import isa.projekat.model.dtos.SegmentDTO;
import isa.projekat.repository.HallRepository;
import isa.projekat.repository.SegmentRepository;

@Service
public class SegmentServiceImpl implements SegmentService {

	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Override
	public void addNewSegment(SegmentDTO segment) {
		// TODO Auto-generated method stub
		Hall h = hallRepository.getHallById(segment.getHallId());
		Segment s = new Segment();
		s.setHall(h);
		s.setName(segment.getSegmentName());
		segmentRepository.save(s);
		h.getSegments().add(s);
		hallRepository.save(h);
	}

	@Override
	public void editSegment(SegmentDTO segment) {
		// TODO Auto-generated method stub
		Segment s = segmentRepository.findSegmentById(segment.getSegmentId());
		s.setName(segment.getSegmentName());;
		segmentRepository.save(s);
	}

	@Override
	public void deleteSegment(SegmentDTO segment) {
		// TODO Auto-generated method stub
		Segment s = segmentRepository.findSegmentById(segment.getSegmentId());
		segmentRepository.delete(s);
	}

}
