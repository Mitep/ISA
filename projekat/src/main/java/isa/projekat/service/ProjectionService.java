package isa.projekat.service;

import java.util.List;

import isa.projekat.model.dtos.ProjectionDTO;

public interface ProjectionService {
	
	List<ProjectionDTO> getProjections(Long cintheId);
	
	void createNewProjection(ProjectionDTO newProj);
	
	void updateProjection(ProjectionDTO editProj);

	void deleteProjection(Long projId);
	
	ProjectionDTO getProjection(Long id);
	
	void fastTicketReserve(Long ticketId, Long userId);
}
