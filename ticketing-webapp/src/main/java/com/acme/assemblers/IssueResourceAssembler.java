package com.acme.assemblers;

import java.util.stream.Collectors;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.controllers.IssueDto;
import com.acme.controllers.IssueHistoryDto;
import com.acme.controllers.IssuesController;
import com.acme.model.Issue;
import com.acme.model.IssueHistory;

@Component
public class IssueResourceAssembler extends ResourceAssemblerSupport<Issue, IssueDto> {

    public IssueResourceAssembler() {
        super(IssuesController.class, IssueDto.class);
    }

    @Override
    public IssueDto toResource(Issue entity) {
        IssueDto dto = createResourceWithId(entity.getId(), entity);
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setUsername(entity.getUsername());
        dto.setState(entity.getState());
        if(entity.getHistory() != null) {
            dto.setHistory(entity.getHistory().stream().map(this::toHistoryDto).collect(Collectors.toList()));
        }
        return dto;
    }

    private IssueHistoryDto toHistoryDto(IssueHistory history) {
        return new IssueHistoryDto(history.getState(), history.getDate());
    }

}