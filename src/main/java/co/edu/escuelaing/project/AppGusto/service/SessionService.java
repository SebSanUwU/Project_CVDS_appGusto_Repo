package co.edu.escuelaing.project.AppGusto.service;


import co.edu.escuelaing.project.AppGusto.model.Session;
import co.edu.escuelaing.project.AppGusto.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    public Session getSession(UUID token){
        return sessionRepository.findByToken(token);
    }
}
