package com.example.Andreas.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "projectApi",
        version = "v1",
        resource = "project",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Andreas.example.com",
                ownerName = "backend.myapplication.Andreas.example.com",
                packagePath = ""
        )
)
public class ProjectEndpoint {

    private static final Logger logger = Logger.getLogger(ProjectEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Project.class);
    }

    /**
     * Returns the {@link Project} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Project} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "project/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Project get(@Named("id") long id) throws NotFoundException {
        logger.info("Getting Project with ID: " + id);
        Project project = ofy().load().type(Project.class).id(id).now();
        if (project == null) {
            throw new NotFoundException("Could not find Project with ID: " + id);
        }
        return project;
    }

    /**
     * Inserts a new {@code Project}.
     */
    @ApiMethod(
            name = "insert",
            path = "project",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Project insert(Project project) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that project.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entities(project.getBugList()).now();
        ofy().save().entity(project).now();
        logger.info("Created Project with ID: " + project.getId());

        return ofy().load().entity(project).now();
    }

    /**
     * Updates an existing {@code Project}.
     *
     * @param id      the ID of the entity to be updated
     * @param project the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Project}
     */
    @ApiMethod(
            name = "update",
            path = "project/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Project update(@Named("id") long id, Project project) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entities(project.getBugList()).now();
        ofy().save().entity(project).now();
        logger.info("Updated Project: " + project);
        return ofy().load().entity(project).now();
    }

    /**
     * Deletes the specified {@code Project}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Project}
     */
    @ApiMethod(
            name = "remove",
            path = "project/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Project.class).id(id).now();
        logger.info("Deleted Project with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "project",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Project> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Project> query = ofy().load().type(Project.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Project> queryIterator = query.iterator();
        List<Project> projectList = new ArrayList<Project>(limit);
        while (queryIterator.hasNext()) {
            projectList.add(queryIterator.next());
        }
        return CollectionResponse.<Project>builder().setItems(projectList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long id) throws NotFoundException {
        try {
            ofy().load().type(Project.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Project with ID: " + id);
        }
    }
}