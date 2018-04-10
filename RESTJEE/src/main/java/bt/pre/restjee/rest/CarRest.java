/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.rest;

import bt.pre.ItemRemote;
import bt.pre.restjee.entity.Car;
import bt.pre.restjee.entity.Cars;
import bt.pre.restjee.service.CarService;
import bt.pre.restjee.service.qualifier.RealCarQualifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;

/**
 *
 * @author tuvshuu
 */
@Stateless
@Path(value = "/car")
@LocalBean
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@RolesAllowed({"TutorialUser", "employee"})
public class CarRest {

//    @EJB
    private ItemRemote itemEJB;

    @Context
    UriInfo uriInfo;

    public CarRest() {
        System.out.println("CarRest is called...");
    }

    @Inject
    @RealCarQualifier
//    @Named("RealCarServiceImpl")
    private CarService carService;

//    @GET
//    @Path("/Item")
//    public Response getItem() {
//        String greeting = itemEJB.greeting("TUVSHUU");
//        return Response.ok("Greeting is - " + greeting).build();
//    }
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @RolesAllowed("TutorialUser")
    public Response getCars(@DefaultValue("application/xml")
            @HeaderParam("Content-type") String contentType,
            @Context Request request) {

        Cars cars = new Cars(carService.getCars());

        // Cache
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(300);

        // 1. modifiedDate
        Date modifiedDate = new Date();
        if (modifiedDate != null) {
//            System.out.println("modifiedDate - " + modifiedDate.toString());

            Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(modifiedDate);
            if (responseBuilder != null) {
                System.out.println("1. Content hasn't been changed");
                responseBuilder.cacheControl(cacheControl);
                return responseBuilder.build();
            }
        }

        // 2.eTag
        EntityTag etag = new EntityTag(Integer.toString(cars.hashCode()));

        System.out.println("etag - " + etag.toString());
        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(etag);
        if (responseBuilder != null) {
            System.out.println("2. Content hasn't been changed");
            responseBuilder.cacheControl(cacheControl);
            responseBuilder.tag(etag);
            return responseBuilder.build();
        }

        if (contentType.equals(MediaType.APPLICATION_JSON)) {
            GenericEntity<List<Car>> carList = new GenericEntity<List<Car>>(cars) {
            };

            return Response.ok(carList, MediaType.APPLICATION_JSON)
                    .header("Last-Modified", new Date())
                    .cacheControl(cacheControl)
                    .tag(etag)
                    .build();
        } else {
            return Response.ok(cars, MediaType.APPLICATION_XML)
                    .header("Last-Modified", new Date())
                    .cacheControl(cacheControl)
                    .tag(etag)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getCar(@HeaderParam("Authorization") String basicAuth,
            @DefaultValue("") @HeaderParam("Content-type") String contentType,
            @PathParam("id") int id) {
        System.out.println("Authorization - " + basicAuth);
        if (basicAuth != null && !basicAuth.isEmpty()) {
            switch (contentType) {
                case MediaType.APPLICATION_JSON:
                    // JSON
                    return Response.ok(carService.getCar(id), MediaType.APPLICATION_JSON).build();
                case MediaType.APPLICATION_XML:
                    // XML
                    return Response.ok(carService.getCar(id), MediaType.APPLICATION_XML).build();
                default:
                    // Unknown
                    List<Variant> variants = new ArrayList<>(Arrays.asList(
                            new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.ENGLISH, "UTF-8"),
                            new Variant(MediaType.APPLICATION_XML_TYPE, Locale.ENGLISH, "UTF-8")));
                    return Response.notAcceptable(variants).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    public Response createCar(@HeaderParam("Authorization") String basicAuth,
            Car car) {
        if (basicAuth != null && !basicAuth.isEmpty()) {
            if (car != null) {
                Integer id;
                if ((id = carService.createCar(car)) != -1) {
                    return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build(id.toString())).build();
                } else {
                    return Response.noContent().build();
                }
            } else {
                throw new BadRequestException("Request body is wrong!");
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCar(@HeaderParam("Authorization") String basicAuth,
            @PathParam("id") int id,
            Car car) {
        if (basicAuth != null && !basicAuth.isEmpty()) {
            if (carService.updateCar(id, car)) {
                return Response.ok().build();
            } else {
                return Response.noContent().build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@HeaderParam("Authorization") String basicAuth, @PathParam("id") int id) {
        if (basicAuth != null && !basicAuth.isEmpty()) {
            if (carService.deleteCar(id)) {
                return Response.ok().build();
            } else {
                return Response.noContent().build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
