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
        name = "bugApi",
        version = "v1",
        resource = "bug",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Andreas.example.com",
                ownerName = "backend.myapplication.Andreas.example.com",
                packagePath = ""
        )
)
public class BugEndpoint {

    private static final Logger logger = Logger.getLogger(BugEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Bug.class);
    }

    /**
     * Returns the {@link Bug} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Bug} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "bug/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Bug get(@Named("id") int id) throws NotFoundException {
        logger.info("Getting Bug with ID: " + id);
        Bug bug = ofy().load().type(Bug.class).id(id).now();
        if (bug == null) {
            throw new NotFoundException("Could not find Bug with ID: " + id);
        }
        return bug;
    }

    /**
     * Inserts a new {@code Bug}.
     */
    @ApiMethod(
            name = "insert",
            path = "bug",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Bug insert(Bug bug) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that bug.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entities(bug.getCommentList()).now();
        ofy().save().entity(bug).now();
        logger.info("Created Bug with ID: " + bug.getId());

        return ofy().load().entity(bug).now();
    }

    /**
     * Updates an existing {@code Bug}.
     *
     * @param id  the ID of the entity to be updated
     * @param bug the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Bug}
     */
    @ApiMethod(
            name = "update",
            path = "bug/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Bug update(@Named("id") int id, Bug bug) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entities(bug.getCommentList()).now();
        ofy().save().entity(bug).now();
        logger.info("Updated Bug: " + bug);
        return ofy().load().entity(bug).now();
    }

    /**
     * Deletes the specified {@code Bug}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code Bug}
     */
    @ApiMethod(
            name = "remove",
            path = "bug/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") int id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(Bug.class).id(id).now();
        logger.info("Deleted Bug with ID: " + id);
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
            path = "bug",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Bug> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Bug> query = ofy().load().type(Bug.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Bug> queryIterator = query.iterator();
        List<Bug> bugList = new ArrayList<Bug>(limit);
        while (queryIterator.hasNext()) {
            bugList.add(queryIterator.next());
        }
        return CollectionResponse.<Bug>builder().setItems(bugList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(int id) throws NotFoundException {
        try {
            ofy().load().type(Bug.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Bug with ID: " + id);
        }
    }
}