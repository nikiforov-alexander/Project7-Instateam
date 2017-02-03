package com.teamtreehouse.instateam;

import com.teamtreehouse.instateam.dao.CollaboratorDao;
import com.teamtreehouse.instateam.dao.ProjectDao;
import com.teamtreehouse.instateam.dao.RoleDao;
import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.ProjectStatus;
import com.teamtreehouse.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    private final ProjectDao projectDao;
    private final CollaboratorDao collaboratorDao;
    private final RoleDao roleDao;

    @Autowired
    public DataLoader(ProjectDao projectDao, CollaboratorDao collaboratorDao, RoleDao roleDao) {
        this.projectDao = projectDao;
        this.collaboratorDao = collaboratorDao;
        this.roleDao = roleDao;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 1; i <= 5; i++) {

            Role role = new Role();
            role.setName("Role " + i);
            roleDao.save(role);

            Collaborator collaborator = new Collaborator();
            collaborator.setName("Collaborator " + i);
            collaborator.setRole(roleDao.findById( (long)i) );
            collaboratorDao.save(collaborator);

            Project project = new Project();
            project.setName("Project " + i);
            project.setDateCreated(new Date((long) i));
            project.setDescription("Description " + i);
            project.setStatus(ProjectStatus.ACTIVE);
            project.setRolesNeeded(
                    Collections.singletonList(
                            roleDao.findById((long) i)
                    )
            );
            project.setCollaboratorsAssigned(
                    Collections.singletonList(
                            collaboratorDao.findById((long) i)
                    )
            );
            projectDao.save(project);
        }
    }
}
